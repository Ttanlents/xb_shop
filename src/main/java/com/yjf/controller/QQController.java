package com.yjf.controller;

import com.yjf.constant.LoginUser;
import com.yjf.entity.Result;
import com.yjf.entity.User;
import com.yjf.service.UserService;
import com.yjf.utils.EmojiUtil;
import com.yjf.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author 余俊锋
 * @date 2020/12/11 18:35
 * @Description
 */
@RestController
public class QQController {

    @Autowired
    Environment environment;
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;


    @RequestMapping("qq_login")
    public void qq_login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String appId = environment.getProperty("app_ID");
        String app_KEY = environment.getProperty("app_KEY");
        String redirect_URI = environment.getProperty("redirect_URI");

        String code = request.getParameter("code");
        System.out.println("qq登录成功，返回code" + code);

        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code" +
                "&client_id=" + appId +
                "&client_secret=" + app_KEY +
                "&code=" + code +
                "&redirect_uri=" + redirect_URI;

        Map jsonObjectForQQ = JsonUtils.getQqAccessToken(url);
        String access_token = (String) jsonObjectForQQ.get("access_token");
        System.out.println("获取token:" + access_token);
        //获取Open_ID
        String url2 = "https://graph.qq.com/oauth2.0/me?access_token=" + access_token;
        Map openIdMap = JsonUtils.getQqOpenId(url2);

        String openid = (String) openIdMap.get("openid");
        System.out.println("获取:" + openid);

        String getUserInfo = "https://graph.qq.com/user/get_user_info?access_token=" + access_token +
                "&oauth_consumer_key=" + appId +
                "&openid=" + openid;
        Map userInfo = JsonUtils.getQqOpenId(getUserInfo);
        System.out.println("用户信息:");
        System.out.println(userInfo);


        //这里开始是我的逻辑了
        User user = userService.getUserByOpenQqId(openid);
        System.out.println(userInfo.get("ret"));
        if (user==null){
            if (userInfo.get("ret").equals(0)) {
                user = new User();
                String userName = (String) userInfo.get("nickname");
                String gender = (String) userInfo.get("gender");
                String headImage = (String) userInfo.get("figureurl_qq");
                // 用户的头像
                user.setPic(headImage);
                // 性别
                user.setGender(gender);
                // 用户的昵称
                String realName = userName;
                String replaceName = EmojiUtil.replaceEmoji(realName);
                user.setRealName(replaceName);
                // 随机用户名(11位随机字符串)
                user.setUsername(UUID.randomUUID().toString().substring(36 - 15));
                user.setQqOpenid(openid);
                // 注册一个新的用户
                user.setRegisterTime(new Date());
                user.setLoginTime(new Date());
                userService.add(user);
                user = userService.getUserByOpenQqId(openid);
            } else {
                System.out.println("状态码:" + userInfo.get("ret"));
            }
        }
        redisTemplate.opsForValue().set(LoginUser.REDIS_LOGINUSER_KEY+user.getId(),user);
        redisTemplate.opsForValue().set(LoginUser.REDIS_WXLOGINUSERID_KEY,user.getId());
        response.sendRedirect("/qqLogin.html");
    }

    @GetMapping("saveQqLoginUser")
    @ResponseBody
    public Result saveQqLoginUser(){
        Integer loginUserId = (Integer) redisTemplate.opsForValue().get(LoginUser.REDIS_WXLOGINUSERID_KEY);
        User user=(User)redisTemplate.opsForValue().get(LoginUser.REDIS_LOGINUSER_KEY+loginUserId);
        return new Result(true,"登录成功",user);
    }
}
