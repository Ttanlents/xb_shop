package com.yjf.dao;

import com.yjf.entity.ShopCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



/**
 * @Description shop_car 数据访问接口
 * @date 2020-12-06 17:22:05
*/
public interface ShopCarDao extends JpaRepository<ShopCar,Integer>,JpaSpecificationExecutor<ShopCar>{

}

