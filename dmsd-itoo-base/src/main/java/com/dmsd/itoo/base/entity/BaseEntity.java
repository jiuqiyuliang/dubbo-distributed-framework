package com.dmsd.itoo.base.entity;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@MappedSuperclass
@EntityListeners(value = { EntityListener.class })
public abstract class BaseEntity implements IdEntity {

	private static final long serialVersionUID = 1L;

	public BaseEntity() {
		super();
	}
	
	/**主键Id*/
	@Id
	@GenericGenerator(name = "uuidGenerator", strategy = "com.dmsd.itoo.base.entity.uuid.Base58UuidGenerator")
	@GeneratedValue(generator = "uuidGenerator")
	@Column(name = "id", length = 22)
	protected String id;

	/**
	 * 数据库名称，不映射
	 */

	@Transient
	private String dataBaseName;

	/**
	 * 备注
	 */
	@Column(name = "comment", length = 255)
	private String comment;

	/**
	 * 操作人
	 */
	@Column(name = "operator", length = 20)
	private String operator;

	/**
	 * 删除标记
	 */
	@Column(name = "isDelete", length = 2)
	private int isDelete = 0;

	/**
	 * 版本开始日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="versionStartTime")
	private Date versionStartTime;

	/**
	 * 版本结束日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="versionEndTime")
	private Date versionEndTime;

	/**
	 * 备注
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * 备注
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * 操作人
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * 操作人
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 版本开始日期
	 */
	public Date getVersionStartTime() {
		return versionStartTime;
	}

	/**
	 * 版本开始日期
	 */
	public void setVersionStartTime(Date versionStartTime) {
		this.versionStartTime = versionStartTime;
	}

	/**
	 * 版本结束日期
	 */
	public Date getVersionEndTime() {
		return versionEndTime;
	}

	/**
	 * 版本结束日期
	 */
	public void setVersionEndTime(Date versionEndTime) {
		this.versionEndTime = versionEndTime;
	}

	public BaseEntity(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getDataBaseName() {
		return dataBaseName;
	}

	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
}
