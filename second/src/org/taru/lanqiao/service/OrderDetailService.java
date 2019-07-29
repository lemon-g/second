package org.taru.lanqiao.service;

import java.util.List;

import org.taru.lanqiao.dao.OrderDetailDaoImpl;
import org.taru.lanqiao.model.Detail;

public class OrderDetailService {
	public List<Detail> queryDetailByOrderId(String orderId){
		OrderDetailDaoImpl daoImpl=new OrderDetailDaoImpl();
		return daoImpl.queryDetailByOrderId(orderId);
	}
}
