package org.taru.lanqiao.service;

import org.taru.lanqiao.dao.UserDaoImpl;
import org.taru.lanqiao.model.User;

public class UserServiceImpl {
	public User login(String uname,String upassword){
		UserDaoImpl userImpl=new UserDaoImpl();
		return userImpl.queryByunameAndUpword(uname, upassword);
	}
	public int reguster(User user){
		UserDaoImpl userImpl=new UserDaoImpl();
		return userImpl.addUserInfo(user);
	}
}
