package com.yjf.controller;

import com.yjf.entity.Result;
import com.yjf.entity.ShopCar;
import com.yjf.service.ShopCarService;
import com.yjf.utils.LoginUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result addShopCar(@RequestBody ShopCar shopCar) {
        ShopCar one = shopCarService.findByUserIdAndPId(shopCar.getUserId(), shopCar.getProductId());
        if (one != null) {
            one.setCount(one.getCount() + shopCar.getCount());
            one.setPrice(one.getPrice()+shopCar.getPrice());
            shopCarService.add(one);
            return new Result(true, "添加成功", null);
        }
        shopCarService.add(shopCar);
        return new Result(true, "添加成功", null);
    }

    @GetMapping
    @RequestMapping("loginUserShopCar")
    public Result loginUserShopCar() {
        List<ShopCar> list = shopCarService.selectAll(LoginUserUtils.getLoginUserId());
        return new Result(true, "查询", list);
    }



    @PutMapping
    @RequestMapping("updateShopCarCount/{shopCarId}/{count}")
    public Result updateShopCarCount(@PathVariable Integer shopCarId,@PathVariable Integer count){
        shopCarService.updateShopCarCount(shopCarId,count);
        return new Result(true, "修改成功", null);
    }

    @DeleteMapping("doDelete/{shopCarId}")
    public Result updateShopCarCount(@PathVariable Integer shopCarId){
        shopCarService.deleteById(shopCarId);
        return new Result(true, "删除成功", null);
    }

}
