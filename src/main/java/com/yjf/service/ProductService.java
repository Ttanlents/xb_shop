package com.yjf.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjf.dao.ProductDao;
import com.yjf.entity.Product;
import com.yjf.mapper.ProductMapper;
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
 * @Description product 服务层
 * @author admin
 * @date 2020-12-06 11:54:33
*/
@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductMapper productMapper;


	/**
	* 查询全部列表
	* @return
	*/
	public List<Product> findAll() {
		return productDao.findAll();
	}

	/**
	* 条件查询+分页
	* @param whereMap
	* @param page
	* @param size
	* @return
	*/
	public Page<Product> findSearch(Map whereMap, int page, int size) {
		Specification<Product> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return productDao.findAll(specification, pageRequest);
	}

	/**
 	* 条件查询
	* @param whereMap
	* @return
	*/
	public List<Product> findSearch(Map whereMap) {
		Specification<Product> specification = createSpecification(whereMap);
		return productDao.findAll(specification);
	}

	/**
	* 根据ID查询实体
	* @param id
	* @return
	*/
	public Product findById(Integer id) {
		return productDao.findById(id).get();
	}

	/**
	* 增加
	* @param product
	*/
	public void add(Product product) {
		productDao.save(product);
	}

	/**
	* 修改
	* @param product
	*/
	public void update(Product product) {
		productDao.save(product);
	}

	/**
	* 删除
	* @param id
	*/
	public void deleteById(Integer id) {
		productDao.deleteById(id);
	}

	/**
	* 动态条件构建
	* @param searchMap
	* @return
	*/
	private Specification<Product> createSpecification(Map searchMap) {

		return new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				//商品类别id
				if (searchMap.get("classId")!=null && !"".equals(searchMap.get("classId"))) {
					predicateList.add(cb.like(root.get("classId").as(String.class), "%"+(String)searchMap.get("classId")+"%"));
				}
				//商品描述
				if (searchMap.get("desc")!=null && !"".equals(searchMap.get("desc"))) {
					predicateList.add(cb.like(root.get("desc").as(String.class), "%"+(String)searchMap.get("desc")+"%"));
				}
				//商品图片
				if (searchMap.get("image")!=null && !"".equals(searchMap.get("image"))) {
					predicateList.add(cb.like(root.get("image").as(String.class), "%"+(String)searchMap.get("image")+"%"));
				}
				//是否热门
				if (searchMap.get("isHot")!=null && !"".equals(searchMap.get("isHot"))) {
					predicateList.add(cb.like(root.get("isHot").as(String.class), "%"+(String)searchMap.get("isHot")+"%"));
				}
				//商品价钱
				if (searchMap.get("price")!=null && !"".equals(searchMap.get("price"))) {
					predicateList.add(cb.like(root.get("price").as(String.class), "%"+(String)searchMap.get("price")+"%"));
				}
				//上架时间
				if (searchMap.get("publishDate")!=null && !"".equals(searchMap.get("publishDate"))) {
					predicateList.add(cb.like(root.get("publishDate").as(String.class), "%"+(String)searchMap.get("publishDate")+"%"));
				}
				//店铺id
				if (searchMap.get("storeId")!=null && !"".equals(searchMap.get("storeId"))) {
					predicateList.add(cb.like(root.get("storeId").as(String.class), "%"+(String)searchMap.get("storeId")+"%"));
				}
				//商品标题
				if (searchMap.get("title")!=null && !"".equals(searchMap.get("title"))) {
					predicateList.add(cb.like(root.get("title").as(String.class), "%"+(String)searchMap.get("title")+"%"));
				}
				//浏览次数
				if (searchMap.get("browsCount")!=null && !"".equals(searchMap.get("browsCount"))) {
					predicateList.add(cb.like(root.get("browsCount").as(String.class), "%"+(String)searchMap.get("browsCount")+"%"));
				}

				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
	}

	public PageInfo<Product> selectAll(String title, Integer isHot, Integer pageCurrent, Integer pageSize,Integer categoryId) {
		com.github.pagehelper.Page<Product> page = PageHelper.startPage(pageCurrent, pageSize);
		List<Product> products = productMapper.selectAll(title, isHot,categoryId);
		PageInfo<Product> pageInfo = new PageInfo<>(products);
		return pageInfo;
	}

	public  Product findImageListById(Integer productId) {
		return productDao.findById(productId).get();
	}
}
