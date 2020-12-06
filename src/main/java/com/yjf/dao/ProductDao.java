package com.yjf.dao;

import com.yjf.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @Description product 数据访问接口
 * @date 2020-12-06 11:54:32
*/
public interface ProductDao extends JpaRepository<Product,Integer>,JpaSpecificationExecutor<Product>{

}

