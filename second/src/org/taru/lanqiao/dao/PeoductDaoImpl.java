package org.taru.lanqiao.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.util.DbUtil;
import org.taru.lanqiao.util.StringUtil;
import org.taru.lanqiao.vo.PageResult;

public class PeoductDaoImpl {
/**
 * 根据商品id查询商品详情
 * @param id
 * @return
 */
	public Product queryProductById(String id){
		
		String sql="select * from product where PRODUCT_ID=?";
		Product product =null;
		List<Map<String, Object>> list=DbUtil.query(sql,id);
		if(list.size()>0){
			product=new Product();
			product.setProductId(StringUtil.valueOf(list.get(0).get("PRODUCT_ID"))); 
			product.setProductName(StringUtil.valueOf(list.get(0).get("PRODUCT_NAME")));
			product.setPoductPhoto(StringUtil.valueOf(list.get(0).get("Product_photo")));
			product.setProductCode(StringUtil.valueOf(list.get(0).get("PRODUCT_CODE")));
			product.setProductCategoryId(StringUtil.valueOf(list.get(0).get("PRODUCT_CATEGORY_ID")));
			product.setProductBrandId(StringUtil.valueOf(list.get(0).get("PRODUCT_BRAND_ID")));
			
//			product.setProductDateTime(StringUtil.valueOf(list.get(0).get("PRODUCT_DATE_TIME")));
			product.setProductIsLuck(StringUtil.valueOf(list.get(0).get("PRODUCT_IS_LACK")));
			product.setProductIsSale(StringUtil.valueOf(list.get(0).get("PRODUCT_IS_SALE")));
			product.setProductLargePrice(StringUtil.valueOf(list.get(0).get("PRODUCT_LARGER_PRICE")));
			product.setProductSuggestPrice(StringUtil.valueOf(list.get(0).get("PRODUCT_SUGGEST_PRICE")));
			product.setProductLargUnit(StringUtil.valueOf(list.get(0).get("PRODUCT_LARGER_UNIT")));
			product.setProductStatus(StringUtil.valueOf(list.get(0).get("PRODUCT_STATUS")));
			product.setProductLargeStandard(StringUtil.valueOf(list.get(0).get("PRODUCT_LARGER_STANDARD")));
			product.setProductOrder(StringUtil.valueOf(list.get(0).get("PRODUCT_ORDER")));
			product.setProductSmallPrce(StringUtil.valueOf(list.get(0).get("PRODUCT_SMALL_PRICE")));
			product.setProductSmallUnit(StringUtil.valueOf(list.get(0).get("PRODUCT_SMALL_UNIT")));
			product.setProductStandard(StringUtil.valueOf(list.get(0).get("PRODUCT_STANDARD")));
			System.out.println(product);
		}
		return product;
	}
	/**
	 * 查询商品列表分页
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public PageResult queryListByPage(int pageSize,int pageNum){
		System.out.println(pageSize+pageNum);
		String sql="select *from product limit ? ,?";
		List<Map<String, Object>>list=DbUtil.query(sql, (pageNum-1)*pageSize,pageSize);
		List<Product> list2=new ArrayList<Product>();
		for(int i=0;i<list.size();i++){
			Product product=new Product();
			product.setProductId(StringUtil.valueOf(list.get(i).get("PRODUCT_ID"))); 
			product.setProductName(StringUtil.valueOf(list.get(i).get("PRODUCT_NAME")));
			product.setPoductPhoto(StringUtil.valueOf(list.get(i).get("Product_photo")));
			product.setProductCode(StringUtil.valueOf(list.get(i).get("PRODUCT_CODE")));
			product.setProductCategoryId(StringUtil.valueOf(list.get(i).get("PRODUCT_CATEGORY_ID")));
			product.setProductBrandId(StringUtil.valueOf(list.get(i).get("PRODUCT_BRAND_ID")));
//			product.setProductDateTime(StringUtil.valueOf(list.get(i).get("PRODUCT_DATE_TIME")));
			product.setProductIsLuck(StringUtil.valueOf(list.get(i).get("PRODUCT_IS_LACK")));
			product.setProductIsSale(StringUtil.valueOf(list.get(i).get("PRODUCT_IS_SALE")));
			product.setProductLargePrice(StringUtil.valueOf(list.get(i).get("PRODUCT_LARGER_PRICE")));
			product.setProductSuggestPrice(StringUtil.valueOf(list.get(i).get("PRODUCT_SUGGEST_PRICE")));
			product.setProductLargUnit(StringUtil.valueOf(list.get(i).get("PRODUCT_LARGER_UNIT")));
			product.setProductStatus(StringUtil.valueOf(list.get(i).get("PRODUCT_STATUS")));
			product.setProductLargeStandard(StringUtil.valueOf(list.get(i).get("PRODUCT_LARGER_STANDARD")));
			product.setProductOrder(StringUtil.valueOf(list.get(i).get("PRODUCT_ORDER")));
			product.setProductSmallPrce(StringUtil.valueOf(list.get(i).get("PRODUCT_SMALL_PRICE")));
			product.setProductSmallUnit(StringUtil.valueOf(list.get(i).get("PRODUCT_SMALL_UNIT")));
			product.setProductStandard(StringUtil.valueOf(list.get(i).get("PRODUCT_STANDARD")));
			list2.add(product);
			
		}
		PageResult pResult=new PageResult();
		pResult.setPageNum(pageNum);
		pResult.setPageSize(pageSize);
		pResult.setList(list2);
		String sql2="select count(*) as row from product";
		List<Map<String, Object>> countList=DbUtil.query(sql2);
		String count=String.valueOf(countList.get(0).get("row"));
		int size=Integer.parseInt(count);
		pResult.setTotal(size);
		pResult.setPages(size%pageNum==0?size/pageNum:size/pageNum+1);
		System.out.println();
		return pResult;
		
	}
	/**
	 * 设置指定货物的缺货状态
	 * @param id
	 * @return
	 */
	public boolean setIsLack(String id){
//		int  islack=0;
		String sql="update product set PRODUCT_IS_LACK=0 where PRODUCT_ID=?";
//		String sql="update product set PRODUCT_IS_LACK="+islack+" where PRODUCT_ID=?";
		int row=DbUtil.update(sql,id);
		if(row!=0){
			return true;
		}
		return false;
	} 
	/**
	 * 重新上架
	 */
	public boolean noLack(String id){
		String sql="update product set PRODUCT_IS_LACK=1 where PRODUCT_ID=?";
		int isLack=DbUtil.update(sql, id);
		if(isLack==0){
			return true;
		}
		
		return false;
	}
	
	
	
	/**
	 * 商品作废或删除
	 * 根据id删除商品（逻辑删除）
	 */
	public boolean delProduct(String id){
		System.out.println(id);
		String sql="update product set PRODUCT_STATUS=0 where PRODUCT_ID=?";
		int isSucces=DbUtil.update(sql, id);
		if(isSucces!=0){	
			return true;
		}
		return false;
	}
	/**
	 * 修改商品
	 */
	public Product updateProduct(String id,Product product){
//		Product product=new Product();
		String sql=
"update product set PRODUCT_NAME='"+product.getProductName()+"' , PRODUCT_PHOTO='"+product.getPoductPhoto()+"' ,PRODUCT_CODE='"+product.getProductCode()+"' ,PRODUCT_CATEGORY_ID='"+product.getProductCategoryId()+"' ,PRODUCT_BRAND_ID='"+product.getProductBrandId()+"' ,PRODUCT_DATE_TIME='"+product.getProductDateTime()+"' ,PRODUCT_IS_LACK='"+product.getProductIsLuck()+"' ,PRODUCT_IS_SALE='"+product.getProductIsSale()+"' ,PRODUCT_SUGGEST_PRICE='"+product.getProductSuggestPrice()+"' ,PRODUCT_LARGER_UNIT='"+product.getProductLargUnit()+"' , PRODUCT_STATUS='"+product.getProductStatus()+"' ,PRODUCT_LARGER_STANDARD='"+product.getProductLargeStandard()+"' ,PRODUCT_ORDER='"+product.getProductOrder()+"' ,PRODUCT_SMALL_PRICE='"+product.getProductSmallPrce()+"' ,PRODUCT_SMALL_UNIT='"+product.getProductSmallUnit()+"' ,PRODUCT_STANDARD='"+product.getProductStandard()+"' WHERE PRODUCT_ID=?";
		

		DbUtil.update(sql, id);
							
		return product;	
	}
	/**
	 * 添加商品
	 */
	public Product addProduct(Product product){
		System.out.println(product.getProductDateTime());
		String sql="insert into product (PRODUCT_ID,PRODUCT_STATUS, "
				+ "PRODUCT_SMALL_PRICE,PRODUCT_SMALL_UNIT,PRODUCT_STANDARD,"
				+ "PRODUCT_ORDER,PRODUCT_LARGER_UNIT,PRODUCT_LARGER_STANDARD,"
				+ "PRODUCT_SUGGEST_PRICE,PRODUCT_LARGER_PRICE,PRODUCT_IS_SALE,"
				+ "PRODUCT_NAME,PRODUCT_IS_LACK,Product_photo,PRODUCT_CODE,"
				+ "PRODUCT_CATEGORY_ID,PRODUCT_BRAND_ID,PRODUCT_DATE_TIME)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		DbUtil.update(sql,
		product.getProductId(),
		product.getProductStatus(),
		product.getProductSmallPrce(),
		product.getProductSmallUnit(),
		product.getProductStandard(),
		product.getProductOrder(),
		product.getProductLargUnit(),
		product.getProductLargeStandard(),
		product.getProductSuggestPrice(),
		product.getProductLargePrice(),
		product.getProductIsSale(),
		product.getProductName(),
		product.getProductIsLuck(),
		product.getPoductPhoto(),
		product.getProductCode(),
		product.getProductCategoryId(),
		product.getProductBrandId(),
		product.getProductDateTime()
		
		);
		if(product!=null){
			return product;
		}else{
			return null;
		}
		
	}
	
	/**
	 * 修改商品图片
	 */

	public boolean updatePicture(){
//		String sql="update product set PRODUCT_PHOTO=? where PRODUCT_ID=?";
		
		return false;
	}
}
