package com.yjf.service;


import com.yjf.dao.UserDao;
import com.yjf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description user 服务层
 * @author admin
 * @date 2020-12-05 18:18:30
*/
@Service
public class UserService {

	@Autowired
	private UserDao userDao;



	/**
	* 查询全部列表
	* @return
	*/
	public List<User> findAll() {
		return userDao.findAll();
	}




	/**
	* 根据ID查询实体
	* @param id
	* @return
	*/
	public User findById(Integer id) {
		return userDao.findById(id).get();
	}

	/**
	* 增加
	* @param user
	*/
	public void add(User user) {
		user.setRegisterTime(new Date());
		userDao.save(user);
	}

	/**
	* 修改
	* @param user
	*/
	public void update(User user) {
		userDao.save(user);
	}

	/**
	* 删除
	* @param id
	*/
	public void deleteById(Integer id) {
		userDao.deleteById(id);
	}


	public User checkUsername(String username) {
		return userDao.findByUsername(username);
	}

	public User checkEmail(String email) {
		return userDao.findByEmail(email);
	}

	public User doLogin(String username, String password) {
		return userDao.findByUsernameAndPassword(username,password);
	}

	public void updateUser(User one) {
		userDao.save(one);
	}


	@Transactional
	public void favoriteProduct(Integer userId, Integer productId) {
		userDao.insertUserFav(userId,productId);
	}
}
