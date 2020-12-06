package com.yjf.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description store实体类
 * @author admin
 * @date 2020-12-06 11:58:57
 */
@Entity
@Table(name="store")
public class Store implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//主键ID


	@Column(name = "store_name")
	private String storeName; //store_name

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer aValue) {
		this.id = aValue;
	}
	
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreName() {
		return this.storeName;
	}

}



