package com.yjf.controller;

import com.yjf.entity.Product;
import com.yjf.entity.Result;
import com.yjf.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

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

    @GetMapping
    @RequestMapping("imagesList/{productId}")
    public Result categoryList(@PathVariable Integer productId){
        Product product = productService.findImageListById(productId);
        String[] split = product.getImageDetail().split(",");
        List<String> list = Arrays.asList(split);
        return new Result(true,"查询成功",list);
    }

}
