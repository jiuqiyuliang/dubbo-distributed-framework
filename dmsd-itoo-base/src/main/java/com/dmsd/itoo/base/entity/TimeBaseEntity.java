package com.dmsd.itoo.base.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;



@MappedSuperclass
public abstract class TimeBaseEntity extends BaseEntity implements TimeEntity{

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	@Column(name = "create_time", nullable = false, updatable = false)
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	@Column(name = "update_time", nullable = false)
	private Date updateTime;

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	protected TimeBaseEntity() {
		super();
		this.createTime = new Date();
		this.updateTime = this.createTime;
	}
}
