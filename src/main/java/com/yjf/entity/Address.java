package com.yjf.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description address实体类
 * @author admin
 * @date 2020-12-08 13:17:47
 */
@Entity
@Table(name="address")
public class Address implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//主键ID

	@Column(name = "user_id")
	private Integer userId; //用户id
	private String userName; //用户名字
	private String area; //地址-区域
	private String addressDetail; //地址-详细地址
	private String postCode; //邮政编码
	private String phone; //电话号码

	@Column(name = "is_default")
	private String isDefault; //1默认地址，0非默认地址

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
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}
	public void setArea(String area) {
		this.area = area;
	}

	public String getArea() {
		return this.area;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getAddressDetail() {
		return this.addressDetail;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPostCode() {
		return this.postCode;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getIsDefault() {
		return this.isDefault;
	}

}



