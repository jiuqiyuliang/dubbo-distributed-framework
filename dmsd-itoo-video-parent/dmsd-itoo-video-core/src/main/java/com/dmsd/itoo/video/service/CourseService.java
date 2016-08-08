package com.dmsd.itoo.video.service;


import com.dmsd.itoo.video.entity.Course;
import com.dmsd.itoo.video.entity.CourseStudent;

public interface CourseService {

    /**
     * 根据学生id查询课程名称  肖红 2016年7月9日
     *
     * @param studentId
     * @return
     */
    public CourseStudent getCourseIdByStudentId(String studentId);

    /**
     * 添加课程 肖红 2016年7月10日16:07:55
     */
    public int addCourse();

    /**
     * 删除课程 肖红 2016年7月19日16:42:47
     * @return
     */
    public int deleteCourseByCourseId(String courseId);

    /**
     * 根据课程id查询课程实体 肖红 2016年7月10日17:47:36
     *
     * @param courseId
     * @return
     */
    public Course findCourseByCourseId(String courseId);


    /**
     * 根据课程id查询课程实体，需要调用基础的服务  肖红 2016年7月11日15:56:36
     *
     * @param courseId
     * @return
     */
    public String getCourseInfoByCourseId(String courseId);
}
