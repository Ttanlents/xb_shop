package com.yjf.mapper;

import com.yjf.entity.ShopCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/12/7 13:45
 * @Description
 */
@Mapper
public interface ShopCarMapper{
    List<ShopCar> selectAll(@Param("userId") Integer userId);
}
