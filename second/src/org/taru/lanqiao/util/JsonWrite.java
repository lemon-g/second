package org.taru.lanqiao.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.vo.JsonResult;

import com.google.gson.Gson;

public class JsonWrite {
	public static void write(HttpServletResponse response,JsonResult result) throws IOException{

		response.setContentType("application/json");
		Gson gson =new Gson();
		String json=gson.toJson(result);
		PrintWriter pw=response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
	}
}
