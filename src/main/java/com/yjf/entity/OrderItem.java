package com.yjf.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description order_item实体类
 * @author admin
 * @date 2020-12-08 17:26:42
 */
@Entity
@Table(name="order_item")
public class OrderItem implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//主键ID

	private Integer count; //该id商品购买数量
	@Column(name="product_id")
	private Integer productId; //商品id

	@Column(name = "total_price")
	private Double totalPrice; //数量 乘以 该商品单价=总价

	@Column(name = "order_id")
	private Integer orderId; //order_id

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer aValue) {
		this.id = aValue;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return this.count;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductId() {
		return this.productId;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

}



