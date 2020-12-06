package com.yjf.dao;

import com.yjf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description user 数据访问接口
 * @date 2020-12-05 18:18:29
*/
public interface UserDao extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User>{

    User findByUsername(String username);

    User findByEmail(String email);

    User findByUsernameAndPassword(String username, String password);

    @Query(value = "INSERT INTO user_favorite(user_id, product_id) VALUES (?1, ?2)",nativeQuery = true)
    @Modifying
    void insertUserFav(Integer userId, Integer productId);
}

