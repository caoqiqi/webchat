package com.tgb.manager;

import java.util.List;

import com.tgb.dao.UserDao;
import com.tgb.entity.Chaty;
import com.tgb.entity.User;

public class UserManagerImpl implements UserManager {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUser(String id) {
		return userDao.getUser(id);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	public boolean delUser(String id) {
		
		return userDao.delUser(id);
	}

	@Override
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}
	
	@Override
	public User checkUser(String userName) {
		return userDao.checkUser(userName);
	}
	
	@Override
	public void addChaty(Chaty chaty) {
		userDao.addChaty(chaty);
	}
	
	@Override
	public List<Chaty> getAllChaty() {
		return userDao.getAllChaty();
	}

}
