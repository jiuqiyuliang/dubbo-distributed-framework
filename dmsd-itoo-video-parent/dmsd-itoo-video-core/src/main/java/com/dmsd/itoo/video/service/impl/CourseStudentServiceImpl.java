package com.dmsd.itoo.video.service.impl;

import com.dmsd.itoo.video.dao.impl.CourseStudentDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmsd.itoo.video.dao.CourseStudentDao;
import com.dmsd.itoo.video.entity.CourseStudent;
import com.dmsd.itoo.video.service.CourseStudentService;
import org.springframework.transaction.annotation.Transactional;

@Service("courseStudentService")
@Transactional
public class CourseStudentServiceImpl implements CourseStudentService {

	//注入的接口是mybatis封装
	@Autowired
	CourseStudentDao courseStudentDao;

	//注入的实现是hibernate封装
	@Autowired
	CourseStudentDaoImpl courseStudentDaoImpl;
	
	/**
	 * 根据学生id查询学生实体 肖红 2016年7月9日
	 */
	@Override
	public CourseStudent findStudent(String id) {

		return courseStudentDaoImpl.find(id);
	}

}
