package com.dmsd.itoo.video.dao.impl;

import com.dmsd.itoo.base.dao.hibernate.BaseHibernateDaoSupport;
import com.dmsd.itoo.video.entity.CourseStudent;
import org.springframework.stereotype.Repository;

@Repository("courseStudentDaoImpl")
public class CourseStudentDaoImpl extends BaseHibernateDaoSupport<CourseStudent> {


	/**
	 * 根据学生id查询课程id 肖红 2016年7月9日
	 */
	public CourseStudent selectCourseIdByStudentId(String studentId) {

		String hql = "From CourseStudent where studentId = ?0";



		CourseStudent courseStudent=this.find(hql,studentId);

		return courseStudent;
	}

}
