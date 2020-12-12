package com.yjf.controller;

import com.yjf.entity.Product;
import com.yjf.entity.Result;
import com.yjf.service.ProductService;
import com.yjf.service.UserService;
import com.yjf.utils.LoginUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 余俊锋
 * @date 2020/12/6 16:30
 * @Description
 */
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;



    @GetMapping
    @RequestMapping("getProductDetailByPId/{productId}")
    public Result getProductDetailByPId(@PathVariable Integer productId){
        //浏览量+1
        productService.updateBrowseCount(productId);

        Product product=productService.getProductDetailByPId(productId);
        String[] split = product.getImageDetail().split(",");
        List<String> list = Arrays.asList(split);
        Integer userId = LoginUserUtils.getLoginUserId();
        Boolean flag= userService.isFavoriteProduct(userId,productId);//是否已经收藏过了
        Map<String, Object> map = new HashMap<>();
        map.put("list",list);
        map.put("flag",flag);
        map.put("product",product);

        return new Result(true,"查询成功",map);
    }






}
