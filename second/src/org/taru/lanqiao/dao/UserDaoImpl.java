package org.taru.lanqiao.dao;

import java.util.List;
import java.util.Map;

import org.taru.lanqiao.model.User;
import org.taru.lanqiao.util.DbUtil;
import org.taru.lanqiao.util.StringUtil;

public class UserDaoImpl {
	/**
	 * 通过应户名和密码查询用户
	 * @param username
	 * @param userPassword
	 * @return
	 */
	public User queryByunameAndUpword(String username,String userPassword){
		String sql="select * from user where user_user_name=? and user_password=?";
		List<Map<String,Object>> list=DbUtil.query(sql, username,userPassword);
		User user=null;
		if(list.size()>0){
			user=new User();
			String uname=StringUtil.valueOf(list.get(0).get("USER_USER_NAME"));
			String password=StringUtil.valueOf(list.get(0).get("USER_PASSWORD"));
			
		}
		return user;
	}
	public int addUserInfo(User user){
	
		String sql="insert into user(USER_USER_NAME , USER_PASSWORD, USER_ADDRESS, USER_ID, USER_TELPHONE,USER_SHOP_NAME,USER_STATUS,USER_COMMENT) values(?,?,?,?,?,?,?,?)";
		int row=DbUtil.update(sql, user.getUserName(),user.getUserPassword(),user.getUserAdd(),user.getUserId(),user.getUserTel(),user.getUserShopName(),user.getUserStatus(),user.getUserComment());
		return row;
	}
}
