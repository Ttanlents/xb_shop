package com.yjf.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description category实体类
 * @author admin
 * @date 2020-12-06 11:28:12
 */
@Entity
@Table(name="category")
public class Category implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//主键ID

	@Column(name = "class_name")
	private String className; //类别名称

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer aValue) {
		this.id = aValue;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return this.className;
	}

	@Override
	public String toString() {
		return "Category{" +
				"id=" + id +
				", className='" + className + '\'' +
				'}';
	}
}



