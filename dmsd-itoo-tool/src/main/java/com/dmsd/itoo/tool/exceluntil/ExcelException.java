package com.dmsd.itoo.tool.exceluntil;

import javax.ejb.ApplicationException;

/**
 * @author     : WH
 * @group      : tgb8
 * @Date       : 2014-6-27 上午11:24:06
 * @Comments   :为Excel导入导出定义的异常类
 * @Version    : 1.0.0
 */
@ApplicationException(rollback=true)
public class ExcelException extends Exception {

	/**
	 * @Fields serialVersionUID	:TODO
	 */
	private static final long serialVersionUID = 7792658926818540301L;

	public ExcelException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExcelException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ExcelException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ExcelException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
