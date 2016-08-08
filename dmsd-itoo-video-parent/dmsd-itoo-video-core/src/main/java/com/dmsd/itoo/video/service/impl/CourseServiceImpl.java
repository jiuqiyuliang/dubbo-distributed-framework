package com.dmsd.itoo.video.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

import com.dmsd.itoo.tool.UUID.UUID22;
import com.dmsd.itoo.video.dao.impl.CourseDaoImpl;
import com.dmsd.itoo.video.dao.impl.CourseStudentDaoImpl;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmsd.itoo.tool.tojson.JackJsonUtils;
import com.dmsd.itoo.video.dao.CourseDao;
import com.dmsd.itoo.video.dao.CourseStudentDao;
import com.dmsd.itoo.video.entity.Course;
import com.dmsd.itoo.video.entity.CourseStudent;
import com.dmsd.itoo.video.service.CourseService;
import org.springframework.transaction.annotation.Transactional;

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService{

	@Autowired
	CourseDao courseDao;

	@Autowired
	CourseDaoImpl courseDaoImpl;

	@Autowired
	CourseStudentDaoImpl courseStudentDaoImpl;

	/**
	 * 根据学生id查询课程名称 肖红 2016年7月9日
	 */
	@Override
	public CourseStudent getCourseIdByStudentId(String studentId) {
		
		return courseStudentDaoImpl.find(studentId);
		
	}

	/**
	 * 添加课程  肖红 2016年7月10日16:08:22
	 */
	@Override
	public int addCourse() {
	
		Course course=new Course();
		course.setId(UUID22.getUUID22());
		course.setCoursename("计算机");
		courseDaoImpl.save(course);
		return 1;
	}

	/**
	 * 删除课程，单个删除课程，调用hibernate封装的delete方法 肖红 2016年7月21日
	 */

	public int deleteCourseByCourseId(String courseId)
	{
		Course course=new Course();
		course.setId(courseId);
		courseDaoImpl.delete(course);
		return 1;
	}


	/**
	 * 根据课程id查询课程实体 肖红 2016年7月10日v1.0
	 * 查询返回的是单个课程实体，用hibernate封装的底层方法即可
	 */
	@Override
	public Course findCourseByCourseId(String courseId) {

		Course course=courseDaoImpl.find(courseId);
		if(course==null){
			return null;
		}
		return course;
	}

	/**
	 * 根据课程id查询课程实体  需要调用基础的服务  肖红 2016年7月11日15:57:15
	 * @throws URISyntaxException 
	 */
	@Override
	public String getCourseInfoByCourseId(String courseId) {
		
		//创建一个httpClient对象

        CloseableHttpClient httpClient=HttpClients.createDefault();
        //创建一个get对象
        URIBuilder uriBuilder;
        CloseableHttpResponse response=null;
        String courseInfo=null;
		try {
			uriBuilder = new URIBuilder("http://192.168.22.247:8091/itoo-basic-course-web/mobile_/course5/courseId/"+courseId);
	        //创建get对象
	        HttpGet get=new HttpGet(uriBuilder.build());
	        //执行请求
	        try {
				response=httpClient.execute(get);
				 //获取响应的结果
		        int statusCode=response.getStatusLine().getStatusCode();
		        System.out.println("状态码为"+statusCode);
		        HttpEntity entity=response.getEntity();
		        //将entity中的内容读入到一个字符串中
		        courseInfo =EntityUtils.toString(entity,"utf-8");
		        System.out.print(courseInfo);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}finally{
			 //关闭httpClient对象
	        try {
				response.close();
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
		}
		return courseInfo;
	}
}