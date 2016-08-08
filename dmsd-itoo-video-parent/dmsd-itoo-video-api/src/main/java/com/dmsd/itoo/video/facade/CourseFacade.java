package com.dmsd.itoo.video.facade;

import com.dmsd.itoo.video.dto.StudentCourse;

public interface CourseFacade {

	/**
	 * 根据学生id查询课程名称 返回数据包括学生code，课程名称，课程id  
	 * 返回的数据为dto的StudentCourse实体
	 * 肖红 2016年7月9日
	 * @param studentId
	 * @return
	 */
	public StudentCourse getStudentCoruseById(String id);
	
	
	
}
