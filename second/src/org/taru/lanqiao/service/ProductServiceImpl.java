package org.taru.lanqiao.service;


import org.taru.lanqiao.dao.PeoductDaoImpl;
import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.vo.PageResult;

public class ProductServiceImpl {
	/**
	 * 根据id查询商品
	 * @param id
	 * @return
	 */
	public Product queryById(String id){
		PeoductDaoImpl dao=new PeoductDaoImpl();
		return dao.queryProductById(id);	
	}
	/**
	 * 分页查询所有商品
	 * @param pagenum
	 * @param pagesize
	 * @return
	 */
	public PageResult queryListAll(int pagenum,int pagesize){
		PeoductDaoImpl dao=new PeoductDaoImpl();
		return dao.queryListByPage(pagesize, pagenum);	
	}
	/**
	 * 设置缺货状态
	 * @param id
	 * @param isLack
	 * @return
	 */
	public boolean setIsLack(String id){
		PeoductDaoImpl dao=new PeoductDaoImpl();	
		return dao.setIsLack(id);
	}
	/**
	 * 商品重新上架
	 */
	public boolean noLack(String id){
		PeoductDaoImpl dao=new PeoductDaoImpl();	
		return dao.noLack(id);
	}
	/**
	 * 商品删除和作废
	 */
	public boolean delProduct(String id){
		PeoductDaoImpl dao=new PeoductDaoImpl();	
		return dao.delProduct(id);
	}
	/**
	 * 修改商品
	 */
	public Product updateProduct(String id,Product product){
		System.out.println(product);
		PeoductDaoImpl dao=new PeoductDaoImpl();	
		return dao.updateProduct(id,product);
	}
	/**
	 * 添加商品
	 */
	public Product addProduct(Product product){
		System.out.println(product.getProductId());
		PeoductDaoImpl dao=new PeoductDaoImpl();
		return dao.addProduct(product);
	}
}
