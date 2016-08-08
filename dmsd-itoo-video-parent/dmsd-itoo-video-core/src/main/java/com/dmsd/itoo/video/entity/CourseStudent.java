package com.dmsd.itoo.video.entity;


import com.dmsd.itoo.base.entity.TimeBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "t_coursestudent")
@Cacheable
public class CourseStudent extends TimeBaseEntity {
    @Column(name = "courseId", insertable = true, updatable = true)
    private String courseid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId", insertable = false, updatable = false)
    private Course course;

    @Column(name = "studentId", nullable = true)
    private String studentid;


    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid == null ? null : courseid.trim();
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid == null ? null : studentid.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CourseStudent other = (CourseStudent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCourseid() == null ? other.getCourseid() == null : this.getCourseid().equals(other.getCourseid()))
                && (this.getStudentid() == null ? other.getStudentid() == null : this.getStudentid().equals(other.getStudentid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCourseid() == null) ? 0 : getCourseid().hashCode());
        result = prime * result + ((getStudentid() == null) ? 0 : getStudentid().hashCode());
        return result;
    }
}