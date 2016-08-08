package com.dmsd.itoo.video.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsd.itoo.base.dao.changedb.DatabaseDefineBean;
import com.dmsd.itoo.base.dao.changedb.MyDataSource;
import com.dmsd.itoo.video.facade.CourseFacade;
import com.dmsd.itoo.video.dto.StudentCourse;
import com.dmsd.itoo.video.entity.Course;
import com.dmsd.itoo.video.entity.CourseStudent;
import com.dmsd.itoo.video.service.CourseService;
import com.dmsd.itoo.video.service.CourseStudentService;

import org.springframework.transaction.annotation.Transactional;

@Service("courseFacade")
@Transactional
public class CourseFacadeImpl implements CourseFacade {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseStudentService courseStudentService;
	
	/**
	 * 根据学生id查询课程名称 返回数据包括学生code，课程名称，课程id 给别的系统提供服务
	 * 返回的数据为dto的StudentCourse实体
	 * 肖红 2016年7月21日
	 */
	@Override
	public StudentCourse getStudentCoruseById(String id) {
		
		MyDataSource.setDefineBeans(new DatabaseDefineBean("jdbc:mysql://192.168.22.215:8066/mysqlcluster","root","root"));
		//根据学生id取得学生实体
		CourseStudent courseStudent=courseStudentService.findStudent(id);
		
		String courseId=courseStudent.getCourseid();//课程id
		String studentCode=courseStudent.getStudentid();//学生code
		//根据课程id取得课程实体
		Course course=courseService.findCourseByCourseId(courseId);
		String courseName=course.getCoursename();
		
		//封装dto实体
		StudentCourse studentCourse=new StudentCourse();
		studentCourse.setCourseId(courseId);
		studentCourse.setStudentCode(studentCode);
		studentCourse.setCourseName(courseName);
		return studentCourse;
	}

}
