package com.yjf.dao;

import com.yjf.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



/**
 * @Description category 数据访问接口
 * @date 2020-12-06 11:28:11
*/
public interface CategoryDao extends JpaRepository<Category,String>,JpaSpecificationExecutor<Category>{

}

