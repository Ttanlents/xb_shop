package com.yjf.service;

import com.yjf.dao.OrderDao;
import com.yjf.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Description order 服务层
 * @author admin
 * @date 2020-12-08 17:25:41
*/
@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;



	/**
	* 查询全部列表
	* @return
	*/
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	/**
	* 条件查询+分页
	* @param whereMap
	* @param page
	* @param size
	* @return
	*/
	public Page<Order> findSearch(Map whereMap, int page, int size) {
		Specification<Order> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return orderDao.findAll(specification, pageRequest);
	}

	/**
 	* 条件查询
	* @param whereMap
	* @return
	*/
	public List<Order> findSearch(Map whereMap) {
		Specification<Order> specification = createSpecification(whereMap);
		return orderDao.findAll(specification);
	}

	/**
	* 根据ID查询实体
	* @param id
	* @return
	*/
	public Order findById(Integer id) {
		return orderDao.findById(id).get();
	}

	/**
	* 增加
	* @param order
	*/
	public void add(Order order) {
		orderDao.save(order);
	}

	/**
	* 修改
	* @param order
	*/
	public void update(Order order) {
		orderDao.save(order);
	}

	/**
	* 删除
	* @param id
	*/
	public void deleteById(Integer id) {
		orderDao.deleteById(id);
	}

	/**
	* 动态条件构建
	* @param searchMap
	* @return
	*/
	private Specification<Order> createSpecification(Map searchMap) {

		return new Specification<Order>() {

			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				//收货地址
				if (searchMap.get("receiveAddress")!=null && !"".equals(searchMap.get("receiveAddress"))) {
					predicateList.add(cb.like(root.get("receiveAddress").as(String.class), "%"+(String)searchMap.get("receiveAddress")+"%"));
				}
				//收货人的名字
				if (searchMap.get("receiveName")!=null && !"".equals(searchMap.get("receiveName"))) {
					predicateList.add(cb.like(root.get("receiveName").as(String.class), "%"+(String)searchMap.get("receiveName")+"%"));
				}
				//订单提交时间
				if (searchMap.get("orderTime")!=null && !"".equals(searchMap.get("orderTime"))) {
					predicateList.add(cb.like(root.get("orderTime").as(String.class), "%"+(String)searchMap.get("orderTime")+"%"));
				}
				//订单收货电话
				if (searchMap.get("phone")!=null && !"".equals(searchMap.get("phone"))) {
					predicateList.add(cb.like(root.get("phone").as(String.class), "%"+(String)searchMap.get("phone")+"%"));
				}
				//订单状态
				if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))) {
					predicateList.add(cb.like(root.get("state").as(String.class), "%"+(String)searchMap.get("state")+"%"));
				}
				//订单总价格
				if (searchMap.get("total")!=null && !"".equals(searchMap.get("total"))) {
					predicateList.add(cb.like(root.get("total").as(String.class), "%"+(String)searchMap.get("total")+"%"));
				}
				//发起订单的用户id
				if (searchMap.get("userId")!=null && !"".equals(searchMap.get("userId"))) {
					predicateList.add(cb.like(root.get("userId").as(String.class), "%"+(String)searchMap.get("userId")+"%"));
				}

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}




	public List<Integer> findIdsByloginUserId(Integer loginUserId) {
		return orderDao.findIdsByLoginUserId(loginUserId);
	}

	@Transactional
	public void updateOrderState(int orderId, Integer recentPay) {
		 orderDao.updateByOrderId(orderId,recentPay);
	}

    public List<Order> findByUserIdAndState(Integer loginUserId, Integer state) {
		return orderDao.findByUserIdAndState(loginUserId,state);
    }
}
