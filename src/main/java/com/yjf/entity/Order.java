package com.yjf.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description order实体类
 * @author admin
 * @date 2020-12-08 17:25:41
 */
@Entity
@Table(name="`order`")
public class Order implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//主键ID

	@Column(name = "receive_address")
	private String receiveAddress; //收货地址

	@Column(name = "receive_name")
	private String receiveName; //收货人的名字
	@Column(name = "order_time")
	private java.util.Date orderTime; //订单提交时间
	private String phone; //订单收货电话
	private Integer state; //订单状态
	private Double total; //订单总价格
	@Column(name = "user_id")
	private Integer userId; //发起订单的用户id

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer aValue) {
		this.id = aValue;
	}
	
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getReceiveAddress() {
		return this.receiveAddress;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceiveName() {
		return this.receiveName;
	}
	public void setOrderTime(java.util.Date orderTime) {
		this.orderTime = orderTime;
	}

	public java.util.Date getOrderTime() {
		return this.orderTime;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getState() {
		return this.state;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotal() {
		return this.total;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

}



