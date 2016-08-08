package com.dmsd.itoo.video.dao;

import com.dmsd.itoo.video.entity.CourseStudent;
import org.springframework.stereotype.Repository;

@Repository("courseStudentDao")
public interface CourseStudentDao{
    /**
     * 根据学生id查询课程id 肖红 
     * @param studentId
     * @return
     */
    CourseStudent selectCourseIdByStudentId(String studentId);
}