package com.yjf.controller;

import com.github.pagehelper.PageInfo;
import com.yjf.constant.LoginUser;
import com.yjf.entity.*;
import com.yjf.service.AddressService;
import com.yjf.service.ProductService;
import com.yjf.service.UserService;
import com.yjf.utils.LoginUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 * @Description user 控制器层
 * @date 2020-12-05 18:18:30
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    HttpSession session;
    @Autowired
    AddressService addressService;
    @Autowired
    ProductService productService;
    @Autowired
    Environment environment;

    /**
     *注册
     * @param user
     */
    @PostMapping("register")
    @ResponseBody
    public Result toRegister(@RequestBody User user) {
        user.setPic("http://192.168.1.120:8080/images/user7.jpg");
        user.setRegisterTime(new Date());
        user.setLoginTime(new Date());
        user.setRealName("Customer person");
        user.setInfo("萌新");
        userService.add(user);
        Address address = new Address();
        address.setIsDefault("1");
        address.setPostCode("516300");
        address.setArea("广东省广州市");
        address.setAddressDetail("13号");
        address.setPhone("xxxxxxxxxxxx");
        address.setUserName(user.getUsername());
        address.setUserId(user.getId());
        addressService.add(address);
        return new Result(true,"注册成功",null);
    }

    /**
     *校验用户名是否存在
    * @param username
    */
    @GetMapping("/checkUsername/{username}")
    @ResponseBody
    public Result checkUsername(@PathVariable String username) {
        User user = userService.checkUsername(username);
        if (user != null) {
            return new Result(true, "用户名已注册", null);
        }
        return new Result(false, "用户名还没有注册", null);
    }

    /**
     *校验邮箱是否存在
     * @param email
     */
    @GetMapping("/checkEmail/{email}")
    @ResponseBody
    public Result checkEmail(@PathVariable String email) {
        User user = userService.checkEmail(email);
        if (user != null) {
            return new Result(true, "邮箱已注册", null);
        }
        return new Result(false, "邮箱还没有注册", null);
    }

    /**
     *登录
     * @param user
     */
    @PostMapping("/doLogin/{code}")
    @ResponseBody
    public Result doLogin(@PathVariable String code, @RequestBody User user) {
        String safeCode = (String) session.getAttribute("safeCode");
        if (!Objects.equals(safeCode, code)) {
            return new Result(false, "验证码错误", null);
        }
        User one = userService.doLogin(user.getUsername(), user.getPassword());
        if (one != null) {
            one.setLoginTime(new Date());
            userService.updateUser(one); //修改登录时间
            one.setPassword(null);
            Map<Object, Object> map = new HashMap<>();
            map.put(LoginUser.STORAGE_LOGINUSER_KEY, one);
            map.put(LoginUser.COOKIE_LOGINUSERID_KEY, one.getId());
            redisTemplate.opsForValue().set(LoginUser.REDIS_LOGINUSER_KEY + one.getId(), one, 3, TimeUnit.DAYS);
            return new Result(true, "登录成功", map);
        }
        return new Result(false, "账号或密码有误", null);
    }

    /**
     *收藏商品   true收藏过了 false没收藏过
     * @param userId  productId
     */
    @GetMapping("/favoriteProduct/{userId}/{productId}")
    @ResponseBody
    public Result favoriteProduct(@PathVariable Integer userId,@PathVariable Integer productId) {
        Boolean flag= userService.isFavoriteProduct(userId,productId);//是否已经收藏过了

        if (flag){
            userService.notFavoriteProduct(userId,productId);//取消收藏
            return new Result(true, "取消收藏成功", false);
        }
        userService.favoriteProduct(userId,productId);//收藏
        return new Result(true, "收藏成功", true);
    }

    /**
     *@Description TODO：获取当前用户信息
     *@author 余俊锋
     *@date 2020/12/8 12:41
     *@params null
     *@return
     */
    @GetMapping("getLoginUserInfo")
    @ResponseBody
    public Result getLoginUserInfo(){
        Integer loginUserId = LoginUserUtils.getLoginUserId();
        User user = userService.getLoginUserInfo(loginUserId);
        List<Address> addressList=addressService.findByUserId(loginUserId);
        Map<String,Object> map=new HashMap<>();
        for (Address address : addressList) {
            if (address.getIsDefault().equals("1")){
                user.setReceiveAddress(address.getArea()+address.getAddressDetail());
            }
        }
        Integer myCarShopCount=userService.myCarShopCount(loginUserId);
        Integer myOrderCount=userService.myOrderCount(loginUserId);
        map.put("user",user);
        map.put("addressList",addressList);
        map.put("myCarShopCount",myCarShopCount);
        map.put("myOrderCount",myOrderCount);
        return new Result(true,"查询成功",map);
    }

    /**
     *@Description TODO:查询用户 的收藏
     *@author 余俊锋
     *@date 2020/12/9 14:46
     *@params
     *@return com.yjf.entity.Result
     */
    @GetMapping("selectUserFavorite/{pageCurrent}")
    public Result selectUserFavorite(@PathVariable Integer pageCurrent){
        Integer loginUserId = LoginUserUtils.getLoginUserId();
        PageInfo<Product> pageInfo = productService.selectUserFavorite(loginUserId,pageCurrent);
        PageResult<Product> pageResult = new PageResult<>(pageInfo.getPages()==0?1:pageInfo.getPages(),pageInfo.getList());
        return new Result(true,"查询成功",pageResult);
    }

    @PutMapping("updateUserInfo")
    public Result updateUserInfo(@RequestBody User user){
        userService.add(user);
        return new Result(true,"修改成功",null);
    }

    @PostMapping("doSaveAddress")
    public Result doSaveAddress(@RequestBody Address address){
        Integer loginUserId = LoginUserUtils.getLoginUserId();
        address.setUserId(loginUserId);
        address.setPostCode("516300");
        Integer addressCount=addressService.countAddress(loginUserId);
        if (addressCount==0){   //一个地址都没有设为  默认地址
            address.setIsDefault("1");
            addressService.add(address);
        }else {
             //已经有地址  不一定一定存在有默认地址
            Address aDefault = addressService.findByUserIdAndDefault(loginUserId, "1");
            if (aDefault!=null){
                if (address.getIsDefault().equals("true")){ //新的地址成为 默认地址
                    aDefault.setIsDefault("0");
                    address.setIsDefault("1");
                    addressService.add(address);
                }else if (aDefault.getId()==address.getId()){ //就是默认地址自己和自己  不想当默认也得是默认
                    address.setIsDefault("1");
                    addressService.add(address);
                }else {
                    address.setIsDefault("0");
                    addressService.add(address);
                }
            }else {
                address.setIsDefault("1");
                addressService.add(address);
            }

        }


        return new Result(true,"添加成功",null);
    }

    @PutMapping("toUpdatePassword")
    public Result toUpdatePassword(String password,String rePassword){
        Integer loginUserId = LoginUserUtils.getLoginUserId();
        User one = userService.findById(loginUserId);
        if (one.getPassword().equals(password)){
            one.setPassword(rePassword);
            userService.add(one);
            return new Result(true,"修改成功",null);
        }
        return new Result(true,"修改失败",null);
    }

    @DeleteMapping("doDeleteAddress")
    public Result doDeleteAddress(Integer id){
        addressService.deleteById(id);
        return new Result(true,"删除成功",null);
    }

    /**
     * QQ登录二维码
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("toQQLogin")
    public void toQQLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //qq应用AppID
        String appId = environment.getProperty("app_ID");
        System.out.println("appid"+appId);
        //qq授权成功后的回调地址
        String redirect_URI = environment.getProperty("redirect_URI");


        //Ste2：获取Authorization Token
        String url = "https://graph.qq.com/oauth2.0/authorize?response_type=code" +
                "&client_id=" + appId +
                "&redirect_uri=" + redirect_URI +
                "&state=test";
        response.sendRedirect(url);
        //将页面重定向到qq第三方的登录页面

    }



}
