package com.qianfeng.mymac.djtu.entityclass;

import java.util.List;

/**
 * Created by mymac on 2016/11/18.
 */

public class ViewP_News {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : topic_f1
         * m_value : 3
         * topic : [{"id":"60477","title":"2016年度茅以升科学技术奖学生奖项评审会暨第十一届高校学生工作论坛在我校举行","image":"http://news.djtu.edu.cn/_uploadimg/image/20161114/20161114163058175817.jpg"},{"id":"60476 ","title":"党委书记刘晓英会见西岗区副区长王敬","image":"http: //news.djtu.edu.cn/_uploadimg/image/20161110/20161110164223982398.jpg"},{"id":"60475 ","title":"我校2016年大学生校园文化节开幕","image":"http: //news.djtu.edu.cn/_uploadimg/image/20161110/20161110161591079107.jpg"},{"id":"60473 ","title":"我校与软件企业联合培养软件人才","image":"http: //news.djtu.edu.cn/_uploadimg/image/20161109/20161109170414651465.jpg"},{"id":"60472 ","title":"我校学子在\u201d中普杯\u201d辽宁省第四届大学生创业大赛中再获佳绩","image":"http: //news.djtu.edu.cn/_uploadimg/image/20161107/20161107172941944194.png"},{"id":"60470","title":"我校召开党委全委（扩大）会议学习贯彻党的十八届六中全会精神","image":"http: //news.djtu.edu.cn/_uploadimg/image/20161102/20161102082695659565.jpg"}]
         */

        private String list;
        private int m_value;
        private List<TopicBean> topic;

        public String getList() {
            return list;
        }

        public void setList(String list) {
            this.list = list;
        }

        public int getM_value() {
            return m_value;
        }

        public void setM_value(int m_value) {
            this.m_value = m_value;
        }

        public List<TopicBean> getTopic() {
            return topic;
        }

        public void setTopic(List<TopicBean> topic) {
            this.topic = topic;
        }

        public static class TopicBean {
            /**
             * id : 60477
             * title : 2016年度茅以升科学技术奖学生奖项评审会暨第十一届高校学生工作论坛在我校举行
             * image : http://news.djtu.edu.cn/_uploadimg/image/20161114/20161114163058175817.jpg
             */

            private String id;
            private String title;
            private String image;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
