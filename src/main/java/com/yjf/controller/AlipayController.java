package com.yjf.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.yjf.config.AlipayConfig;
import com.yjf.constant.PayState;
import com.yjf.entity.Order;
import com.yjf.entity.Result;
import com.yjf.service.OrderService;
import com.yjf.utils.LoginUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("aliPay")
public class AlipayController {
    /**
     * 支付宝支付
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填  自定义的（时间戳）
     * @param total_amount 付款金额，必填
     * @param subject 订单名称，必填
     * @param body 商品描述，可空
     * @return
     */
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    OrderService orderService;
    @PostMapping("/pay")
    public Result pay(Integer orderId, String subject, String body) throws AlipayApiException {
        Order order = orderService.findById(orderId);
        List<Order> orderList=new ArrayList<>();
        orderList.add(order);
        //存订单号到redis
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String out_trade_no=dateFormat.format(new Date());  //告诉的支付宝订单id

        redisTemplate.opsForValue().set("order_id:"+":"+out_trade_no,orderList,1,TimeUnit.DAYS);


        double total_amount=order.getTotal();
        System.out.println("拿到付款金额"+total_amount);


        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println("我是result"+result);
        orderService.updateOrderState(orderId, PayState.RECENT_PAY);  //修改订单状态为  已经支付
        return new Result(true,"支付成功",result);
    }

    @PostMapping("/payAll")
    public Result payAll(String subject, String body) throws AlipayApiException {
        Integer loginUserId = LoginUserUtils.getLoginUserId();
        List<Order> orderList = orderService.findByUserIdAndState(loginUserId,1);
        //存订单号到redis
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String out_trade_no=dateFormat.format(new Date());  //告诉的支付宝订单id

        redisTemplate.opsForValue().set("order_id:"+":"+out_trade_no,orderList,1, TimeUnit.DAYS);


        double total_amount=0d;
        for (Order order : orderList) {
            total_amount+=order.getTotal();
        }
        System.out.println("拿到付款金额"+total_amount);


        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println("我是result"+result);
        for (Order order : orderList) {
            orderService.updateOrderState(order.getId(), PayState.RECENT_PAY);  //修改订单状态为  已经支付
        }
        return new Result(true,"支付成功",result);
    }

    @RequestMapping(value = "/paySuccess",method= RequestMethod.POST)
    public void notify_url(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, String[]> paramsMap =request.getParameterMap();
        Map params=new HashMap();
        for (String key:paramsMap.keySet()){
            String[]  value=  paramsMap.get(key);
            String values="";
            for (int i=0;i<value.length;i++){
                values=(i==value.length-1)?values+value[i]:values+value[i]+","; //如果同名参数有多个  用逗号隔开
            }
            params.put(key,values);
        }
        //将异步通知中收到的所有参数都存放到map中
        boolean signVerified=false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type) ;//调用SDK验证签名
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            String trade_no=request.getParameter("trade_no");
            String trade_status=request.getParameter("trade_status");
            System.out.println(trade_no);
            System.out.println(trade_status);
            if (trade_status.equals("TRADE_SUCCESS")||trade_status.equals("TRADE_FINISHED")){
                Integer loginUserId = LoginUserUtils.getLoginUserId();
                String out_trade_no = request.getParameter("out_trade_no");
                System.out.println(out_trade_no);
                List<Order> orders=(List<Order>) redisTemplate.opsForValue().get("order_id:"+":"+out_trade_no);
                for (Order order : orders) {
                    System.out.println("订单:"+order.getId());
                }
                if(orders!=null&&orders.size()>0){
                    //dosomething
                    for (Order order : orders) {
                        orderService.updateOrderState(order.getId(),PayState.RECENT_PAY);
                    }
                    System.out.println("交易成功!");
                }
                PrintWriter out= response.getWriter();
                out.write("success");
            }
        }else{
            System.out.println("交易失败");
            PrintWriter out= response.getWriter();
            out.write("failture");
        }
        request.getServletContext().removeAttribute("order_id"+request.getParameter("out_trade_no"));
    }


}
