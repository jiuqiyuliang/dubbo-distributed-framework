package com.dmsd.itoo.tool.tojson;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class outToJson {
	
	private static final String encoding = "UTF-8";
	private static final String contentType = "application/json;charset=UTF-8";

	
	/**
	 * 输出json格式的结果
	 *
	 * @param response
	 *            用于输出
	 * @param result
	 *            用于输入的json字符串
	 */
	public static void outJson(HttpServletResponse response, String result) {

		response.setContentType(contentType);
		response.setCharacterEncoding(encoding);
		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
