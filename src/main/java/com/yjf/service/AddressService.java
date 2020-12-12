package com.yjf.service;

import com.yjf.dao.AddressDao;
import com.yjf.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Description address 服务层
 * @author admin
 * @date 2020-12-08 13:17:47
*/
@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;



	/**
	* 查询全部列表
	* @return
	*/
	public List<Address> findAll() {
		return addressDao.findAll();
	}

	/**
	* 条件查询+分页
	* @param whereMap
	* @param page
	* @param size
	* @return
	*/
	public Page<Address> findSearch(Map whereMap, int page, int size) {
		Specification<Address> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return addressDao.findAll(specification, pageRequest);
	}

	/**
 	* 条件查询
	* @param whereMap
	* @return
	*/
	public List<Address> findSearch(Map whereMap) {
		Specification<Address> specification = createSpecification(whereMap);
		return addressDao.findAll(specification);
	}

	/**
	* 根据ID查询实体
	* @param id
	* @return
	*/
	public Address findById(Integer id) {
		return addressDao.findById(id).get();
	}

	/**
	* 增加
	* @param address
	*/
	public void add(Address address) {
		addressDao.save(address);
	}

	/**
	* 修改
	* @param address
	*/
	public void update(Address address) {
		addressDao.save(address);
	}

	/**
	* 删除
	* @param id
	*/
	public void deleteById(Integer id) {
		addressDao.deleteById(id);
	}

	/**
	* 动态条件构建
	* @param searchMap
	* @return
	*/
	private Specification<Address> createSpecification(Map searchMap) {

		return new Specification<Address>() {

			@Override
			public Predicate toPredicate(Root<Address> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				//用户id
				if (searchMap.get("userId")!=null && !"".equals(searchMap.get("userId"))) {
					predicateList.add(cb.like(root.get("userId").as(String.class), "%"+(String)searchMap.get("userId")+"%"));
				}
				//用户名字
				if (searchMap.get("userName")!=null && !"".equals(searchMap.get("userName"))) {
					predicateList.add(cb.like(root.get("userName").as(String.class), "%"+(String)searchMap.get("userName")+"%"));
				}
				//地址-区域
				if (searchMap.get("area")!=null && !"".equals(searchMap.get("area"))) {
					predicateList.add(cb.like(root.get("area").as(String.class), "%"+(String)searchMap.get("area")+"%"));
				}
				//地址-详细地址
				if (searchMap.get("addressDetail")!=null && !"".equals(searchMap.get("addressDetail"))) {
					predicateList.add(cb.like(root.get("addressDetail").as(String.class), "%"+(String)searchMap.get("addressDetail")+"%"));
				}
				//邮政编码
				if (searchMap.get("postCode")!=null && !"".equals(searchMap.get("postCode"))) {
					predicateList.add(cb.like(root.get("postCode").as(String.class), "%"+(String)searchMap.get("postCode")+"%"));
				}
				//电话号码
				if (searchMap.get("phone")!=null && !"".equals(searchMap.get("phone"))) {
					predicateList.add(cb.like(root.get("phone").as(String.class), "%"+(String)searchMap.get("phone")+"%"));
				}
				//1默认地址，0非默认地址
				if (searchMap.get("isDefault")!=null && !"".equals(searchMap.get("isDefault"))) {
					predicateList.add(cb.like(root.get("isDefault").as(String.class), "%"+(String)searchMap.get("isDefault")+"%"));
				}

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

	public List<Address> findByUserId(Integer loginUserId) {
		return addressDao.findByUserId(loginUserId);
	}

	public Address findByUserIdAndDefault(Integer loginUserId,String isDefault) {
		return addressDao.findByUserIdAndIsDefault(loginUserId,isDefault);
	}

	public Integer countAddress(Integer loginUserId) {
		return addressDao.countAddress(loginUserId);
	}
}
