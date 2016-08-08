package com.dmsd.itoo.tool.tojson;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 转换json的工具类
 *
 * @author 陈丽娜
 * @version 1.0.0 , 2014年12月23日 下午3:24:33
 */
public class JacksonJsonUntil {

	// 定义日志工具
	private static final Logger logger = Logger
			.getLogger(JacksonJsonUntil.class);
	public JsonGenerator jsonGenerator = null;
	public ObjectMapper objectMapper = null;

	/**
	 * 将对象转换为json字符串
	 *
	 * @param response
	 *            向前台传递数据时的格式
	 * @param obj
	 *            需要转换的对象（可以为list）
	 * @throws Exception
	 *             异常处理
	 */
	public void beanToJson(HttpServletResponse response, Object obj) {
		response.setContentType("application/json;charset=UTF-8");
		objectMapper = new ObjectMapper();
		try {
			String json = objectMapper.writeValueAsString(obj);
			PrintWriter out = response.getWriter();
			out.write(json);
		} catch (Exception e) {
			logger.error("对象转Json失败!!!");
			e.printStackTrace();
		}
	}

	/**
	 * 将对象转换为json字符串（专门用于json跨域问题）
	 *
	 * @param response
	 *            向前台传递数据时的格式
	 * @param obj
	 *            需要转换的对象（可以为list）
	 * @throws Exception
	 *             异常处理
	 */
	public void beanToUrlJson(HttpServletRequest request,
			HttpServletResponse response, Object obj) {
		response.setContentType("text/plain");
		objectMapper = new ObjectMapper();
		String callbackFunName = request.getParameter("callbackparam");// 得到js函数名称
		try {
			String json = objectMapper.writeValueAsString(obj);
			PrintWriter out = response.getWriter();
			out.write(callbackFunName + "(" + json + ")");
			logger.info("对象转Json（专用于解决json跨域问题）：" + callbackFunName + "("
					+ json + ")");
		} catch (IOException e) {
			logger.error("对象转Json失败!!!");
			e.printStackTrace();
		}

	}
}
