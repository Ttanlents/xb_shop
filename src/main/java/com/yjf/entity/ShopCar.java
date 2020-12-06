package com.yjf.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description shop_car实体类
 * @author admin
 * @date 2020-12-06 17:22:06
 */
@Entity
@Table(name="shop_car")
public class ShopCar implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//主键ID

	@Column(name = "user_id")
	private Integer userId; //用户id

	@Column(name="product_id")
	private Integer productId; //商品id
	private Integer count; //数量
	private Double price; //价钱

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer aValue) {
		this.id = aValue;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductId() {
		return this.productId;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return this.count;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return this.price;
	}

	@Override
	public String toString() {
		return "ShopCar{" +
				"id=" + id +
				", userId=" + userId +
				", productId=" + productId +
				", count=" + count +
				", price=" + price +
				'}';
	}
}



