package com.qianfeng.mymac.djtu.entityclass2;

import java.util.List;

/**
 * Created by mymac on 2016/12/24.
 */

public class GradeList {

    /**
     * gradelist : [{"cource":"数分","grade":"65","id":"101","term":"1"},{"cource":"计算机基础","grade":"88","id":"102","term":"1"},{"cource":"体育","grade":"90","id":"103","term":"1"},{"cource":"马哲","grade":"62","id":"104","term":"1"},{"cource":"高代","grade":"45","id":"105","term":"1"},{"cource":"几何","grade":"75","id":"106","term":"1"}]
     * term : 1
     */

    private String term;
    private List<GradelistBean> gradelist;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public List<GradelistBean> getGradelist() {
        return gradelist;
    }

    public void setGradelist(List<GradelistBean> gradelist) {
        this.gradelist = gradelist;
    }

    public static class GradelistBean {
        /**
         * cource : 数分
         * grade : 65
         * id : 101
         * term : 1
         */

        private String cource;
        private String grade;
        private String id;
        private String term;

        public String getCource() {
            return cource;
        }

        public void setCource(String cource) {
            this.cource = cource;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }
    }
}
