package com.yjf.mapper;

import com.yjf.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 余俊锋
 * @date 2020/12/6 12:05
 * @Description
 */
@Mapper
public interface ProductMapper {


    List<Product> selectAll(@Param("title") String title,@Param("isHot") Integer isHot,@Param("categoryId") Integer categoryId);
}
