package com.dmsd.itoo.tool.pageModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

public class PageEntity<T>  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//**************************分页公共************************************
	//页码
	private int pageNum;
	//页大小
	private int pageSize;
	//记录数
	private Long total;
	//**************************单实体分页保存到rows中，原生sql语句分页查询保存在list中************************************
	//页集合
	private List<T> rows;
	//最终查询条件
	private String hql;
	//**************************多实体分页保存到Map中，通过属性取值************************************
	//查询内容
	private String selectContent;
	//查询实体
	private String selectFrom;
	//查询条件
	private String selectWhere;
	//查询到的内容
	private Map<Object, Object> map = new HashMap<Object, Object>();


	public String getSelectContent() {
		return selectContent;
	}


	public void setSelectContent(String selectContent) {
		this.selectContent = selectContent;
	}


	public String getSelectFrom() {
		return selectFrom;
	}


	public void setSelectFrom(String selectFrom) {
		this.selectFrom = selectFrom;
	}


	public String getSelectWhere() {
		return selectWhere;
	}


	public void setSelectWhere(String selectWhere) {
		this.selectWhere = selectWhere;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Map<Object, Object> getMap() {
		return map;
	}


	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}
	
	/**
	 * 数据库名称
	 */

	@Transient
	private String dataBaseName;

	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}

	
}
