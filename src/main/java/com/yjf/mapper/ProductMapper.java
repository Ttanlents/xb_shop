package com.yjf.mapper;

import com.yjf.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 余俊锋
 * @date 2020/12/6 12:05
 * @Description
 */
@Mapper
public interface ProductMapper {


    List<Product> selectAll(@Param("title") String title,@Param("isHot") Integer isHot,@Param("categoryId") Integer categoryId);
    Product selectOne(@Param("id") Integer id);

    List<Map<String,Object>> selectLoginUserNotPayOrder(Integer id);

    List<Map<String,Object>> selectAllOrder(@Param("id") Integer id,@Param("state") Integer state);

    List<Product> selectUserFavorite(Integer id);
}
