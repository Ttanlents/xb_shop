package com.yjf.service;

import com.yjf.dao.OrderItemDao;
import com.yjf.entity.OrderItem;
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
 * @author admin
 * @Description order_item 服务层
 * @date 2020-12-08 17:26:42
 */
@Service
public class OrderItemService {

    @Autowired
    private OrderItemDao orderItemDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<OrderItem> findAll() {
        return orderItemDao.findAll();
    }

    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<OrderItem> findSearch(Map whereMap, int page, int size) {
        Specification<OrderItem> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return orderItemDao.findAll(specification, pageRequest);
    }

    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<OrderItem> findSearch(Map whereMap) {
        Specification<OrderItem> specification = createSpecification(whereMap);
        return orderItemDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public OrderItem findById(Integer id) {
        return orderItemDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param orderItem
     */
    public void add(OrderItem orderItem) {
        orderItemDao.save(orderItem);
    }

    /**
     * 修改
     *
     * @param orderItem
     */
    public void update(OrderItem orderItem) {
        orderItemDao.save(orderItem);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(Integer id) {
        orderItemDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<OrderItem> createSpecification(Map searchMap) {

        return new Specification<OrderItem>() {

            @Override
            public Predicate toPredicate(Root<OrderItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                //该id商品购买数量
                if (searchMap.get("count") != null && !"".equals(searchMap.get("count"))) {
                    predicateList.add(cb.like(root.get("count").as(String.class), "%" + (String) searchMap.get("count") + "%"));
                }
                //商品id
                if (searchMap.get("productId") != null && !"".equals(searchMap.get("productId"))) {
                    predicateList.add(cb.like(root.get("productId").as(String.class), "%" + (String) searchMap.get("productId") + "%"));
                }
                //数量 乘以 该商品单价=总价
                if (searchMap.get("totalPrice") != null && !"".equals(searchMap.get("totalPrice"))) {
                    predicateList.add(cb.like(root.get("totalPrice").as(String.class), "%" + (String) searchMap.get("totalPrice") + "%"));
                }
                //order_id
                if (searchMap.get("orderId") != null && !"".equals(searchMap.get("orderId"))) {
                    predicateList.add(cb.like(root.get("orderId").as(String.class), "%" + (String) searchMap.get("orderId") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    @Transactional
    public void deleteByProductIdAndPId(Integer orderId, Integer productId) {
        orderItemDao.deleteByProductIdAndPId(orderId, productId);
    }

    public Integer countByOrderId(Integer orderId) {
        return orderItemDao.countByOrderId(orderId);
    }
}
