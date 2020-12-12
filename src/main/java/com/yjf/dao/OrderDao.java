package com.yjf.dao;

import com.yjf.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description order 数据访问接口
 * @date 2020-12-08 17:25:40
*/
public interface OrderDao extends JpaRepository<Order,Integer>,JpaSpecificationExecutor<Order>{

    @Query("update Order o set o.state=?2 where o.id=?1")
    @Modifying
      void updateByOrderId(Integer id,Integer state);

    @Query("select o.id from Order o where o.userId=?1")
    List<Integer> findIdsByLoginUserId(Integer loginUserId);

    List<Order> findByUserIdAndState(Integer userId, Integer state);
}

