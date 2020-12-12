package com.yjf.dao;

import com.yjf.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @Description address 数据访问接口
 * @date 2020-12-08 13:17:46
*/
public interface AddressDao extends JpaRepository<Address,Integer>,JpaSpecificationExecutor<Address>{

    List<Address> findByUserId(Integer loginUserId);

    Address findByUserIdAndIsDefault(Integer userId,String isDefault);

    @Query("select count(1) from Address a where a.userId=?1")
    Integer countAddress(Integer loginUserId);
}

