package com.yjf.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 余俊锋
 * @date 2020/12/6 17:48
 * @Description
 */
@Entity
@Table(name = "user_favorite")
@IdClass(UserFavorite.class)
public class UserFavorite implements Serializable {

    @Id
    @Column(name="user_id")
    private Integer  userId;
    @Id
    @Column(name="product_id")
    private Integer productId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "UserFavorite{" +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
