package org.taru.lanqiao.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.taru.lanqiao.model.Detail;
import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.util.DbUtil;
import org.taru.lanqiao.util.StringUtil;

public class OrderDetailDaoImpl {
/**
 * 根据订单Id查询订单详情
 * @param orderId
 * @return
 */
	public List<Detail> queryDetailByOrderId(String orderId){
		String sql="select od.*,product.PRODUCT_ID,product.PRODUCT_NAME from order_detail od left  join product on od.DETAIL_PRODUCT_ID=product.PRODUCT_ID where od.DETAIL_ORDER_ID=?";
		List<Map<String ,Object>> list=DbUtil.query(sql, orderId);
		System.out.println("list.length="+list.size());
		List<Detail> details=new ArrayList<Detail>();
		if(list.size()>0){
			for (int i=0;i<list.size();i++) {
				Detail detail=new Detail();
				Product product=new Product();
				detail.setDetailOrderId(StringUtil.valueOf(list.get(i).get("DETAIL_ORDER_ID")));
				detail.setDetailComment(StringUtil.valueOf(list.get(i).get("DETAIL_COMMENT")));
				detail.setDetailDateTime(StringUtil.valueOf(list.get(i).get("DETAIL_DATE_TIME")));
				detail.setDetailId(StringUtil.valueOf(list.get(0).get("DETAIL_ID")));
				detail.setDetailProductCount(StringUtil.valueOf(list.get(i).get("DETAIL_PRODUCT_COUNT")));
				detail.setDetailProductId(StringUtil.valueOf(list.get(i).get("DETAIL_PRODUCT_ID")));
				detail.setDetailProductPrice(StringUtil.valueOf(list.get(i).get("DETAIL_PRODUCT_PRICE")));
				detail.setDetailProductUnit(StringUtil.valueOf(list.get(i).get("DETAIL_PRODUCT_UNIT")));
				detail.setDetailStatus(StringUtil.valueOf(list.get(i).get("DETAIL_STATUS")));
				detail.setDetailTotalPrice(StringUtil.valueOf(list.get(i).get("DETAIL_TOTAL_PRICE")));
				detail.setDetailUserId(StringUtil.valueOf(list.get(0).get("DETAIL_USER_ID")));
				product.setProductId(StringUtil.valueOf(list.get(i).get("PRODUCT_ID")));
				product.setProductName(StringUtil.valueOf(list.get(i).get("PRODUCT_NAME")));
				detail.setProduct(product);
				details.add(detail);
			}
			System.out.println("size()="+details.size());
		}
		return details;
	}
	
}
