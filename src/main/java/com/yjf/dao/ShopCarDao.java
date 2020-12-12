package com.yjf.dao;

import com.yjf.entity.ShopCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @Description shop_car 数据访问接口
 * @date 2020-12-06 17:22:05
*/
public interface ShopCarDao extends JpaRepository<ShopCar,Integer>,JpaSpecificationExecutor<ShopCar>{

    ShopCar findByUserIdAndProductId(Integer userId, Integer productId);

    @Modifying
    @Query("update ShopCar sc set sc.count=sc.count+1 where sc.userId=?1 and sc.productId=?2")
    void updateCountByUserIdAndPId(Integer userId, Integer productId);

    @Modifying
    @Query("update ShopCar sc set sc.count=?2 where sc.id=?1")
    void updateShopCarCount(Integer shopCarId, Integer count);

    List<ShopCar> findByUserId(Integer id);
}

