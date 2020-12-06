package com.yjf.controller;

import com.yjf.entity.Result;
import com.yjf.entity.ShopCar;
import com.yjf.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 余俊锋
 * @date 2020/12/6 17:26
 * @Description
 */
@RestController
@RequestMapping("shopCar")
public class ShopCarController {

    @Autowired
    ShopCarService shopCarService;


    @PutMapping
    @RequestMapping("addShopCar")
    public Result addShopCar(@RequestBody ShopCar shopCar){
        shopCarService.add(shopCar);
        return new Result(true,"添加成功",null);
    }

}
