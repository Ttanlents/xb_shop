package com.yjf.controller;

import com.yjf.constant.PayState;
import com.yjf.entity.*;
import com.yjf.service.*;
import com.yjf.utils.LoginUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 余俊锋
 * @date 2020/12/8 18:40
 * @Description
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ShopCarService shopCarService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ProductService productService;

    @PostMapping("addOrder/{shopCarId}")
    public Result toWaitPay(@PathVariable Integer shopCarId) {
        User loginUser = LoginUserUtils.getLoginUser();
        ShopCar shopCar = shopCarService.findById(shopCarId);
        Address address = addressService.findByUserIdAndDefault(loginUser.getId(), "1");

        if (address==null){
            return new Result(false, "你还没有设置默认地址", null);
        }

        //生成订单
        Order one = new Order();
        one.setOrderTime(new Date());
        one.setPhone(address.getPhone());
        one.setReceiveAddress(address.getArea() + address.getAddressDetail());
        one.setReceiveName(address.getUserName());
        one.setState(PayState.NOT_PAY);
        one.setUserId(loginUser.getId());
        one.setTotal(shopCar.getPrice());
        orderService.add(one);
        Integer orderId = one.getId(); //订单id

        //生成明细
        OrderItem orderItem = new OrderItem();
        orderItem.setCount(shopCar.getCount());
        orderItem.setOrderId(orderId);
        orderItem.setProductId(shopCar.getProductId());
        orderItem.setTotalPrice(shopCar.getPrice());
        orderItemService.add(orderItem);

        //清空购物车
        shopCarService.deleteById(shopCarId);
        return new Result(true, "提交订单成功，请支付", null);
    }

    @PostMapping("addOrderAll")
    public Result addOrderAll() {
        User loginUser = LoginUserUtils.getLoginUser();
        List<ShopCar> shopCarList = shopCarService.findByUserId(loginUser.getId());
        Address address = addressService.findByUserIdAndDefault(loginUser.getId(), "1");
        if (address==null){
            return new Result(false, "你还没有设置默认地址", null);
        }
        //生成订单
        Order one = new Order();
        one.setOrderTime(new Date());
        one.setPhone(address.getPhone());
        one.setReceiveAddress(address.getArea() + address.getAddressDetail());
        one.setReceiveName(address.getUserName());
        one.setState(PayState.NOT_PAY);
        one.setUserId(loginUser.getId());
        Double allMoney = 0d;
        for (ShopCar car : shopCarList) {
            allMoney += car.getPrice();
        }
        one.setTotal(allMoney);
        orderService.add(one);
        Integer orderId = one.getId(); //订单id

        for (ShopCar shopCar : shopCarList) {
            //生成明细
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(shopCar.getCount());
            orderItem.setOrderId(orderId);
            orderItem.setProductId(shopCar.getProductId());
            orderItem.setTotalPrice(shopCar.getPrice());
            orderItemService.add(orderItem);
        }

        //清空购物车
        for (ShopCar shopCar : shopCarList) {
            shopCarService.deleteById(shopCar.getId());
        }
        return new Result(true, "提交订单成功，请支付", null);
    }


    /**
     * @return com.yjf.entity.Result
     * @Description TODO:查询用户 未支付的订单
     * @author 余俊锋
     * @date 2020/12/9 14:46
     * @params
     */
    @GetMapping("selectNotPay")
    public Result toWaitPay() {
        Integer loginUserId = LoginUserUtils.getLoginUserId();
        List<Map<String, Object>> list = productService.selectLoginUserNotPayOrder(loginUserId);
        return new Result(true, "查询成功", list);
    }

    /**
     * @return com.yjf.entity.Result
     * @Description TODO:查询用户 所有的订单
     * @author 余俊锋
     * @date 2020/12/9 14:46
     * @params
     */
    @GetMapping("selectAllOrder")
    public Result selectAllOrder() {
        Integer loginUserId = LoginUserUtils.getLoginUserId();
        List<Map<String, Object>> list = productService.selectAllOrder(loginUserId, null);
        List<Map<String, Object>> wait = productService.selectAllOrder(loginUserId, 3);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("waitEval", wait);
        return new Result(true, "查询成功", map);
    }

    @PutMapping("confirmReceive/{orderId}")
    public Result confirmReceive(@PathVariable Integer orderId) {
        orderService.updateOrderState(orderId, PayState.WAIT_EVALUATION);
        return new Result(true, "修改成功", null);
    }

    @PutMapping("toEvaluation/{orderId}")
    public Result toEvaluation(@PathVariable Integer orderId) {
        orderService.updateOrderState(orderId, PayState.RECENT_EVALUATION);
        return new Result(true, "评价成功", null);
    }

    @DeleteMapping("doDelete/{orderId}/{productId}")
    public Result doDelete(@PathVariable Integer orderId, @PathVariable Integer productId) {
        orderItemService.deleteByProductIdAndPId(orderId, productId);
        Integer count = orderItemService.countByOrderId(orderId);
        if (count==0){
            orderService.deleteById(orderId);
        }
        return new Result(true, "删除成功", null);
    }

}
