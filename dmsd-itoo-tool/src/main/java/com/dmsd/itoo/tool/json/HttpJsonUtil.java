package com.dmsd.itoo.tool.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JsonConfig;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Title: HttpJsonUtil.java
 * @Package com.dmsd.itoo.tool.json
 * @Description: JsonUtil 工具类
 * @author mike
 * @version V1.0
 */
public class HttpJsonUtil {

	// -- header 常量定义 --//
	private final String HEADER_ENCODING = "encoding";
	private final String HEADER_NOCACHE = "no-cache";
	private final String DEFAULT_ENCODING = "UTF-8";
	private final boolean DEFAULT_NOCACHE = true;
	private Logger logger = LoggerFactory.getLogger(HttpJsonUtil.class);

	private ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);

	/**
	  * @Description : 当从request中取数据，并且没有参数名，从inputStream中取
	  * @创建人：mike
	  * @创建时间：2015年4月29日 下午3:43:47  
	  * @return String
	  * @throws
	 */
	public String getDataFromRequestWithStream(HttpServletRequest httpServletRequest) {

		String str = "";
		try {
			int streamLength = httpServletRequest.getInputStream().available();

			InputStream is = httpServletRequest.getInputStream();
			if(null == is){
				return null;
			}

			byte[] bytes = new byte[1024 * 1024];

			int nRead = 1;
			int nTotalRead = 0;
			while (nRead > 0) {
				nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
				if (nRead > 0)
					nTotalRead = nTotalRead + nRead;
			}
			str = new String(bytes);
			str = new String(bytes, 0, nTotalRead, "UTF-8");

//			InputStream  inputStream = httpServletRequest.getInputStream();
//			inputStream.
//			str = new String(inputStream);

			str = URLDecoder.decode(str, "UTF-8");
			
		} catch (IOException e) {
			logger.error("***************************************************************");
			logger.error("***************************************************************");
			logger.error("***************************************************************");
			logger.error("JsonUtil - > getDataFromRequestWithStream() -> 出错了.....");
			logger.error("***************************************************************");
			logger.error("***************************************************************");
			logger.error("***************************************************************");
			e.getMessage();
			e.printStackTrace();
		}
		
		return str;
	}
	
	/**
	 * 直接输出JSON,使用Jackson转换Java对象.
	 * 
	 * @param data
	 *            可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #(String, String, String...)
	 */
	public void renderJson(final Object data,final HttpServletResponse httpServletResponse, final String... headers) {
		HttpServletResponse response = initResponseHeader(ServletUtils.JSON_TYPE, httpServletResponse, headers);
		try {
			PrintWriter writer = response.getWriter();
			mapper.writeValue(writer, data);
			response.getWriter().flush();
		} catch (IOException e) {
//			throw new IllegalArgumentException(e);
//			e.printStackTrace();
		}
	}
	
	/**
	 * 分析并设置contentType与headers.
	 */
	private HttpServletResponse initResponseHeader(final String contentType,final HttpServletResponse httpServletResponse, final String... headers) {
		// 分析headers参数
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for (String header : headers) {
			String headerName = StringUtils.substringBefore(header, ":");
			String headerValue = StringUtils.substringAfter(header, ":");

			if (StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else {
				throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
			}
		}
		HttpServletResponse response = httpServletResponse;

		// 设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			ServletUtils.setDisableCacheHeader(response);
		}

		return response;
	}

	
	public static JsonConfig getJsonConfig() {

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());

//		jsonConfig.registerDefaultValueProcessor(Integer.class, type -> "");
//
//		jsonConfig.registerDefaultValueProcessor(Double.class, type -> "");

		return jsonConfig;
	}
}
