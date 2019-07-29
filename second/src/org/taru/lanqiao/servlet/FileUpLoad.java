package org.taru.lanqiao.servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.taru.lanqiao.util.JsonWrite;
import org.taru.lanqiao.vo.JsonResult;



/**
 * 上传文件
 * 上传参数名任意
 * @author Administrator
 *
 */
@WebServlet("/api/upload")
public class FileUpLoad extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // 判断是普通表单，还是文件上传表单
	    if (!ServletFileUpload.isMultipartContent(request)) {
	        throw new RuntimeException("不是文件上传表单！");
	    }

	    // 创建上传所需要的两个对象
	    DiskFileItemFactory factory = new DiskFileItemFactory();  // 磁盘文件对象
	    ServletFileUpload sfu = new ServletFileUpload(factory);   // 文件上传对象

	    // 设置解析文件上传中的文件名的编码格式
	    sfu.setHeaderEncoding("utf-8");

	    // 创建 list容器用来保存 表单中的所有数据信息
	    List<FileItem> items = new ArrayList<FileItem>();

	    // 将表单中的所有数据信息放入 list容器中
	    try {
	        items = sfu.parseRequest(request);
	    } catch (FileUploadException e) {
	        e.printStackTrace();
	    }

	    // 遍历 list容器，处理 每个数据项 中的信息
	    for (FileItem item : items) {
	        // 判断是否是普通项
	        if (item.isFormField()) {
	            // 处理 普通数据项 信息
	            handleFormField(item,response);
	        } else {
	            // 处理 文件数据项 信息
	            handleFileField(item,response);
	        }
	    }
	}
	/**
	 * 处理 普通数据项
	 * @param item
	 */
	private void handleFormField(FileItem item, HttpServletResponse response) {
	    // 获取 普通数据项中的 name值
	    String fieldName = item.getFieldName();

	    // 获取 普通数据项中的 value值
	    String value = "";
	    try {
	        value = item.getString("utf-8");  // 以 utf-8的编码格式来解析 value值
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }

	    // 输出到控制台
	    System.out.println("fieldName:" + fieldName + "--value:" + value);
	}
	
	/**
	 * 处理 文件数据项
	 * @param item
	 */
	private void handleFileField(FileItem item, HttpServletResponse response) {
	    // 获取 文件数据项中的 文件名
	    String fileName = item.getName();

	    // 判断 此文件的文件名是否合法
	    if (fileName==null || "".equals(fileName)) {
	        return;
	    }

	    // 控制只能上传图片
	    if (!item.getContentType().startsWith("image")) {
	        return;
	    }

	    // 将文件信息 输出到控制台
	    System.out.println("fileName:" + fileName);         // 文件名
	    System.out.println("fileSize:" + item.getSize());   // 文件大小

	    // 设置存储路径
	    String path ="G:\\photo";
	    System.out.println("path:"+path);
	    File file = new File(path);   // 创建 file对象

	    // 创建 /files 目录
	    if (!file.exists()) {
	        file.mkdir();
	    }

	    // 将文件保存到服务器上（UUID是通用唯一标识码，不用担心会有重复的名字出现）
	    try {
	        item.write(new File(file.toString(), UUID.randomUUID()+"_"+fileName));
	        JsonResult result=new JsonResult("200", "上传图片成功", UUID.randomUUID()+"_"+fileName);
	        JsonWrite.write(response, result);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
}
