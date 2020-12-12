package com.yjf.dao;

import com.yjf.entity.OrderItem;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description order_item 数据访问接口
 * @date 2020-12-08 17:26:41
*/
public interface OrderItemDao extends JpaRepository<OrderItem,Integer>,JpaSpecificationExecutor<OrderItem>{

    @Modifying
    @Query("delete from OrderItem  o where o.productId=?2 and o.orderId=?1")
    void deleteByProductIdAndPId(Integer orderId, Integer productId);

    @Query("select count(1) from OrderItem o where o.orderId=?1")
    Integer countByOrderId(Integer orderId);
}

