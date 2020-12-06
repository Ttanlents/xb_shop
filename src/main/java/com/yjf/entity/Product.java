package com.yjf.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description product实体类
 * @author admin
 * @date 2020-12-06 11:54:33
 */
@Entity
@Table(name="product")
public class Product implements Serializable{

	@Id
	private Integer id;//主键ID

	@Column(name = "class_id")
	private Integer classId; //商品类别id

	@Transient
	private String className;

	@Column(name = "`desc`")
	private String desc; //商品描述
	private String image; //商品图片

	@Column(name = "is_hot")
	private Integer isHot; //是否热门
	private Double price; //商品价钱

	@Column(name = "publish_date")
	private java.util.Date publishDate; //上架时间

	@Column(name="store_id")
	private Integer storeId; //店铺id

	@Transient
	private String storeName;

	private String title; //商品标题

	@Column(name = "brows_count")
	private Integer browsCount; //浏览次数

	@Column(name = "image_detail")
	private String imageDetail;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getImageDetail() {
		return imageDetail;
	}

	public void setImageDetail(String imageDetail) {
		this.imageDetail = imageDetail;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer aValue) {
		this.id = aValue;
	}
	
	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getClassId() {
		return this.classId;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return this.desc;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return this.image;
	}
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Integer getIsHot() {
		return this.isHot;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return this.price;
	}
	public void setPublishDate(java.util.Date publishDate) {
		this.publishDate = publishDate;
	}

	public java.util.Date getPublishDate() {
		return this.publishDate;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getStoreId() {
		return this.storeId;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
	public void setBrowsCount(Integer browsCount) {
		this.browsCount = browsCount;
	}

	public Integer getBrowsCount() {
		return this.browsCount;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", classId=" + classId +
				", className='" + className + '\'' +
				", desc='" + desc + '\'' +
				", image='" + image + '\'' +
				", isHot=" + isHot +
				", price=" + price +
				", publishDate=" + publishDate +
				", storeId=" + storeId +
				", storeName='" + storeName + '\'' +
				", title='" + title + '\'' +
				", browsCount=" + browsCount +
				", imageDetail='" + imageDetail + '\'' +
				'}';
	}
}



