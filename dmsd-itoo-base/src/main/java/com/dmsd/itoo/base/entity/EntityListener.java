package com.dmsd.itoo.base.entity;

import java.util.Date;

import javax.persistence.PrePersist;

public class EntityListener {

	@PrePersist
	public void prePersist(BaseEntity baseEntity){
		baseEntity.setVersionStartTime(new Date());
	}
}
