package com.dmsd.itoo.base.entity;

import java.util.Date;

public interface TimeEntity extends IdEntity{


	/**
	 * 获得创建时间
	 * 
	 * @return
	 */
	Date getCreateTime();

	/**
	 * 获得更新时间
	 * 
	 * @return
	 */
	Date getUpdateTime();

	/**
	 * 设置更新时间
	 * 
	 * @param updateTime
	 */
	void setUpdateTime(Date updateTime);
}
