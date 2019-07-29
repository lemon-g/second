package org.taru.lanqiao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebFilter("/*")
public class CorsFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest res=(HttpServletRequest)request;
		HttpServletResponse respon=(HttpServletResponse)response;//
		
		respon.setHeader("Access-Control-Allow-Origin",res.getHeader("Origin")); //*不允许携带凭证 （cookie）  

		/* 允许跨域的请求方法GET, POST, HEAD 等 */
		respon.setHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET");    // //*不允许携带凭证 （例如cookie）  
		
		/* 重新预检验跨域的缓存时间 (s) */
		respon.setHeader("Access-Control-Max-Age", "3600");  
		/* 允许跨域的请求头 */
		respon.setHeader("Access-Control-Allow-Headers", "*");  
		/* 是否携带cookie */
		respon.setHeader("Access-Control-Allow-Credentials", "true");  
		
		chain.doFilter(res, respon);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
