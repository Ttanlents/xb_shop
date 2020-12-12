package com.yjf.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description user实体类
 * @author admin
 * @date 2020-12-05 18:18:30
 */
@Entity
@Table(name="user")
public class User implements Serializable{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;//主键ID


	private String email; //email

	@Column(name="real_name")
	private String realName; //real_name
	private String password; //password
	private String phone; //phone
	private String username; //username

	@Column(name="register_time")
	private Date registerTime; //register_time

	@Column(name="login_time")
	private Date loginTime; //login_time
	private String pic; //pic
	private String info; //info
	private String gender; //gender
	private Integer age; //age

	@Transient
	private String receiveAddress;

	@Column(name="qq_openid")
	private String qqOpenid; //qq_openid

	@Column(name="wx_openid")
	private String wxOpenid; //wx_openid

	@Column(name="wb_openid")
	private String wbOpenid; //wb_openid

	private String address;

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer aValue) {
		this.id = aValue;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRealName() {
		return this.realName;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}
	public void setRegisterTime(java.util.Date registerTime) {
		this.registerTime = registerTime;
	}

	public java.util.Date getRegisterTime() {
		return this.registerTime;
	}
	public void setLoginTime(java.util.Date loginTime) {
		this.loginTime = loginTime;
	}

	public java.util.Date getLoginTime() {
		return this.loginTime;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPic() {
		return this.pic;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfo() {
		return this.info;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return this.gender;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return this.age;
	}
	public void setQqOpenid(String qqOpenid) {
		this.qqOpenid = qqOpenid;
	}

	public String getQqOpenid() {
		return this.qqOpenid;
	}
	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

	public String getWxOpenid() {
		return this.wxOpenid;
	}
	public void setWbOpenid(String wbOpenid) {
		this.wbOpenid = wbOpenid;
	}

	public String getWbOpenid() {
		return this.wbOpenid;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", email='" + email + '\'' +
				", realName='" + realName + '\'' +
				", password='" + password + '\'' +
				", phone='" + phone + '\'' +
				", username='" + username + '\'' +
				", registerTime=" + registerTime +
				", loginTime=" + loginTime +
				", pic='" + pic + '\'' +
				", info='" + info + '\'' +
				", gender=" + gender +
				", age=" + age +
				", qqOpenid='" + qqOpenid + '\'' +
				", wxOpenid='" + wxOpenid + '\'' +
				", wbOpenid='" + wbOpenid + '\'' +
				'}';
	}
}



