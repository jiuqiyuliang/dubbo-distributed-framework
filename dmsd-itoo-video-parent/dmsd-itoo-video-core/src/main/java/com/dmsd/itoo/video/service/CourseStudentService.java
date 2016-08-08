package com.dmsd.itoo.video.service;

import com.dmsd.itoo.video.entity.CourseStudent;

public interface CourseStudentService {

	/**
	 * 根据学生id查询课程id 肖红
	 * @param studentId
	 * @return
	 */
	public CourseStudent findStudent(String id);
}
