package org.taru.lanqiao.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DbUtil {

	
	private static ThreadLocal<Connection> local=new ThreadLocal<Connection>(); //线程的本地变量
	private static Properties properties=new Properties();
	static{
		
		try {
			properties.load(DbUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * 负责增删改查
	 * @throws ClassNotFoundException 
	 */
	//1 加载驱动
	static{
		try {
			
			Class.forName(properties.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 *建立连接
 * @return
 */
	public static Connection openConnection() {
		Connection con=local.get();
		
		try {
			if(con==null || con.isClosed()) {
				con=DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("username"),properties.getProperty("password"));
				System.out.println("打开连接成功");
				local.set(con);
			}
		} catch (SQLException e) {
		
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return con;
	}

	

	
	

	public static  int delete(String sql,Object...params){    //删除数据
	
		return 0;
	}


	/**
	 * DML
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public static  int update(String sql,Object...params){          //修改数据
		Connection conn=openConnection();
		PreparedStatement prt=null;
		int row ;
		try {
			prt=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					prt.setObject(i+1, params[i]);
				}
			}
			row=prt.executeUpdate();
			System.out.println(row);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			throw new RuntimeException("执行dml异常");
			
		}finally{
			if(prt!=null){
				try {
					prt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
				
		return row;
	}
	
	/**
	 * DQL
	 * @param sql
	 * @param params
	 * @return
	 */
	public static  List<Map<String, Object>> query(String sql,Object...params){      //查询数据
		Connection conn=openConnection();
		ResultSet rs=null;
		PreparedStatement pct=null;
		List <Map<String, Object>>list=new ArrayList<Map<String,Object>>();
		try {
			
			pct=conn.prepareStatement(sql);
			if(params!=null){
				
				for(int i=0;i<params.length;i++){
					pct.setObject(i+1, params[i]);
				}
			}
			
			rs=pct.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData(); //结果集的列
			
			int count=rsmd.getColumnCount();
			while(rs.next()){
				Map<String, Object> map=new HashMap<String,Object>();
				for(int i=0;i<count;i++){
					map.put(rsmd.getColumnLabel(i+1), rs.getObject(i+1));
				}
				list.add(map);
			
			}
		} catch (SQLException e) {
			
			throw new RuntimeException("执行dml异常");
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pct!=null){
				try {
					pct.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public static void close(){
		Connection conn=local.get();
		if(conn!=null){
			try {
				conn.close();
				local.remove();
				conn=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
