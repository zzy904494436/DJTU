package com.qianfeng.mymac.djtu.entityclass2;

import java.util.List;

/**
 * Created by mymac on 2016/12/24.
 */

public class CourseList {

    private List<CourselistBean> courselist;

    public List<CourselistBean> getCourselist() {
        return courselist;
    }

    public void setCourselist(List<CourselistBean> courselist) {
        this.courselist = courselist;
    }

    public static class CourselistBean {
        /**
         * course : 模糊数学
         * days : 1
         * id : 101
         * place : J544
         * teacher : 玉宇梅
         * time : 1
         */

        private String course;
        private String days;
        private String id;
        private String place;
        private String teacher;
        private String time;

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
