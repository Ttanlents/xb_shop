package com.yjf.service;

import com.yjf.dao.ShopCarDao;
import com.yjf.entity.ShopCar;
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
 * @Description shop_car 服务层
 * @author admin
 * @date 2020-12-06 17:22:06
*/
@Service
public class ShopCarService {

	@Autowired
	private ShopCarDao shopCarDao;



	/**
	* 查询全部列表
	* @return
	*/
	public List<ShopCar> findAll() {
		return shopCarDao.findAll();
	}

	/**
	* 条件查询+分页
	* @param whereMap
	* @param page
	* @param size
	* @return
	*/
	public Page<ShopCar> findSearch(Map whereMap, int page, int size) {
		Specification<ShopCar> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return shopCarDao.findAll(specification, pageRequest);
	}

	/**
 	* 条件查询
	* @param whereMap
	* @return
	*/
	public List<ShopCar> findSearch(Map whereMap) {
		Specification<ShopCar> specification = createSpecification(whereMap);
		return shopCarDao.findAll(specification);
	}

	/**
	* 根据ID查询实体
	* @param id
	* @return
	*/
	public ShopCar findById(Integer id) {
		return shopCarDao.findById(id).get();
	}

	/**
	* 增加
	* @param shopCar
	*/
	public void add(ShopCar shopCar) {
		shopCarDao.save(shopCar);
	}

	/**
	* 修改
	* @param shopCar
	*/
	public void update(ShopCar shopCar) {
		shopCarDao.save(shopCar);
	}

	/**
	* 删除
	* @param id
	*/
	public void deleteById(Integer id) {
		shopCarDao.deleteById(id);
	}

	/**
	* 动态条件构建
	* @param searchMap
	* @return
	*/
	private Specification<ShopCar> createSpecification(Map searchMap) {

		return new Specification<ShopCar>() {

			@Override
			public Predicate toPredicate(Root<ShopCar> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				//用户id
				if (searchMap.get("userId")!=null && !"".equals(searchMap.get("userId"))) {
					predicateList.add(cb.like(root.get("userId").as(String.class), "%"+(String)searchMap.get("userId")+"%"));
				}
				//商品id
				if (searchMap.get("productId")!=null && !"".equals(searchMap.get("productId"))) {
					predicateList.add(cb.like(root.get("productId").as(String.class), "%"+(String)searchMap.get("productId")+"%"));
				}
				//数量
				if (searchMap.get("count")!=null && !"".equals(searchMap.get("count"))) {
					predicateList.add(cb.like(root.get("count").as(String.class), "%"+(String)searchMap.get("count")+"%"));
				}
				//价钱
				if (searchMap.get("price")!=null && !"".equals(searchMap.get("price"))) {
					predicateList.add(cb.like(root.get("price").as(String.class), "%"+(String)searchMap.get("price")+"%"));
				}

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

}
