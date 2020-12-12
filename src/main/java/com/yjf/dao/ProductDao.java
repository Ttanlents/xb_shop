package com.yjf.dao;

import com.yjf.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


/**
 * @Description product 数据访问接口
 * @date 2020-12-06 11:54:32
*/
public interface ProductDao extends JpaRepository<Product,Integer>,JpaSpecificationExecutor<Product>{

    @Modifying
    @Query("update Product p set p.browsCount=p.browsCount+1 where p.id=?1")
    void updateBrowseCount(Integer productId);
}

