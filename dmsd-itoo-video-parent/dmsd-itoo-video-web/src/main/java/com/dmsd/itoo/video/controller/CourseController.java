package com.dmsd.itoo.video.controller;


import com.dmsd.itoo.base.dao.changedb.DatabaseDefineBean;
import com.dmsd.itoo.base.dao.changedb.MyDataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dmsd.itoo.video.dto.StudentCourse;
import com.dmsd.itoo.video.entity.Course;
import com.dmsd.itoo.video.facade.CourseFacade;
import com.dmsd.itoo.video.service.CourseService;

import ch.qos.logback.classic.Logger;


//sdsdsdsd
@RequestMapping("/course")
@Controller
public class CourseController {
	
	
	//日志处理 肖红 打印到了D盘
	private static Logger logger=(Logger) LoggerFactory.getLogger(CourseController.class);
	
	@Autowired
	CourseService courseService;

	@Autowired
	CourseFacade courseFacade;
	/**
	 * 首页导向页  肖红 2016年7月6日
	 * @paramname
	 * @return
	 */
	@RequestMapping(value={"{name}"},method=RequestMethod.GET)
	public String showCourse(@PathVariable("name")String name){
		return "courseList";
	}
	
	/**
	 * 添加课程 肖红 2016年7月10日 v1.0
	 * 该方法添加的是课程实体，直接调用用hibernate的封装的底层方法即可
	 */
	@RequestMapping(value={"/course1"},method=RequestMethod.POST)
	public void addCourse(){ 
		MyDataSource.setDefineBeans(new DatabaseDefineBean("jdbc:mysql://192.168.22.215:8066/mysqlcluster","root","root"));
		int result=courseService.addCourse();
	}
	
	
	/**
	 * 根据课程id查询课程名称 肖红 v1.0
	 * 该方法返回的是课程实体，用hibernate封装的底层方法即可
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value={"/course2/courseId/{courseId}"},method=RequestMethod.GET)
	public String findCourseByCourseId(@PathVariable("courseId") String courseId){
		MyDataSource.setDefineBeans(new DatabaseDefineBean("jdbc:mysql://192.168.22.215:8066/mysqlcluster","root","root"));
		Course course= courseService.findCourseByCourseId(courseId);
		System.out.println("课程名称为：=================》"+course.getCoursename());
		logger.info("这个地方输出日志信息！");
		return "result";
	}
	
	
	
	/**
	 * 删除课程  切库例子  刘颖
	 * @param id
	 */
	@RequestMapping(value = {"/deleteCourse/{courseId}"},method=RequestMethod.POST)
	public void deleteCourseByCourseId(@PathVariable("courseId")String courseId)
	{
		//String databasename = Request.getSession.getAttribute("databasename");
		String url="jdbc:mysql://192.168.22.215:8066/mysqlcluster";
		String userName="root";
		String passWord="root";
		MyDataSource.setDefineBeans(new DatabaseDefineBean(url,userName,passWord));
		//MyDataSource.setDefineBeans(new DatabaseDefineBean("com.mysql.jdbc.Driver","jdbc:mysql://192.168.22.215:8066/"+dataBaseName+"?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull","root","root"));
		int result=courseService.deleteCourseByCourseId(courseId);
	}
	
	/**
	 * 根据学生id查询课程名称 返回数据包括学生code，课程名称，课程id 给别的系统提供服务
	 * 返回的数据为dto的StudentCourse实体
	 * @param studentId
	 */
	@RequestMapping(value={"/course3/id/{id}"},method=RequestMethod.GET)
	public void getCourseNameByStudentId(@PathVariable("id") String id){
		MyDataSource.setDefineBeans(new DatabaseDefineBean("jdbc:mysql://192.168.22.215:8066/mysqlcluster","root","root"));
		
		System.out.println("学生id是："+id);
		StudentCourse studentCourse=courseFacade.getStudentCoruseById(id);
		System.out.println("学生code为："+studentCourse.getStudentCode());
		System.out.println("课程id为："+studentCourse.getCourseId());
		System.out.println("课程名称为："+studentCourse.getCourseName());
	}
	
	/**
	 * 根据课程id查看课程信息 远程调用基础服务 肖红 2016年7月11日17:58:16
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value={"/course5/courseId/{courseId}"},method=RequestMethod.GET)
	public String getCourseInfoByCourseId(@PathVariable("courseId")String courseId){
		MyDataSource.setDefineBeans(new DatabaseDefineBean("jdbc:mysql://192.168.22.215:8066/mysqlcluster","root","root"));
		String json=courseService.getCourseInfoByCourseId(courseId);
		return json;
	}
	
}
