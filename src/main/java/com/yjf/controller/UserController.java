package com.yjf.controller;

import com.yjf.constant.LoginUser;
import com.yjf.entity.Result;
import com.yjf.entity.User;
import com.yjf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    /**
     *注册
     * @param user
     */
    @PostMapping("register")
    @ResponseBody
    public Result toRegister(@RequestBody User user) {
        userService.add(user);
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
     *收藏商品
     * @param userId  productId
     */
    @GetMapping("/favoriteProduct/{userId}/{productId}")
    @ResponseBody
    public Result favoriteProduct(@PathVariable Integer userId,@PathVariable Integer productId) {
        userService.favoriteProduct(userId,productId);
        return new Result(true, "收藏成功", null);
    }
}
