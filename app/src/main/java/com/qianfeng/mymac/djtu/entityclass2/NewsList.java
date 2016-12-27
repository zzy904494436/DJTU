package com.qianfeng.mymac.djtu.entityclass2;

import java.util.List;

/**
 * Created by mymac on 2016/12/24.
 */

public class NewsList {

    /**
     * nnlist : [{"content":"为深入学习贯彻党的十八届六中全会精神，12月14日下午，我校在图书馆报告厅举行专题培训，由校党委书记","cover":"http://news.djtu.edu.cn/_uploadimg/image/20161223/201612231706001111.jpg","from":"党政办公室","id":101,"time":"2016年12月19日","title":"我校举办学习贯彻十八届六中全会精神干部培训班","type":"1","url":"http://news.djtu.edu.cn/list/detail.asp?m=3&id=60495"},{"content":"2016年12月7日至8日，全国高校思想政治工作会议在北京召开，习近平总书记出席会议并发表重要讲话。为深入学习贯彻落实习总书记讲话精神，认真落实","cover":"http://news.djtu.edu.cn/_uploadimg/image/20161219/2016121918090170170.jpg","from":"党校","id":102,"time":"2016年12月23日","title":"我校召开学习贯彻全国高校思想政治工作会议精神暨辅导员培训班经验交流会","type":"1","url":"http://news.djtu.edu.cn/list/detail.asp?m=3&id=60497"},{"content":"党的十八届六中全会召开后，按照学校党委的统一部署，我校基层党组织迅速行动，精心组织","cover":null,"from":"党委宣传部","id":103,"time":"2016年12月19日","title":"我校基层党组织认真学习贯彻党的十八届六中全会精神","type":"1","url":"http://news.djtu.edu.cn/list/detail.asp?m=3&id=60494"},{"content":"2016年12月15日，刘晓英同志主持召开中共大连交通大学第一届委员会第52次常委（扩大）会议。党委中心组学习了全国高校思","cover":"http://news.djtu.edu.cn/_uploadimg/image/20161216/20161216165284728472.jpg","from":"党政办公室","id":104,"time":"2016年12月16日","title":"学校召开党委常委（扩大）会议 学习贯彻全国高校思想政治工作会议精神","type":"1","url":"http://news.djtu.edu.cn/list/detail.asp?m=3&id=60492"},{"content":"12月13日下午，工程教育认证试点专业工作推进会在图书馆第一会议室召开。副校长关天民、教务处、工程教育认证办公室相关人员","cover":"http://news.djtu.edu.cn/_uploadimg/image/20161216/20161216164941544154.jpg","from":"工程教育认证办公室","id":105,"time":"2016年12月16日","title":"我校召开工程教育认证试点专业工作推进会","type":"1","url":"http://news.djtu.edu.cn/list/detail.asp?m=3&id=60491"},{"content":"12月16日上午，我校全体纪委委员在726会议室集中学习《中国共产党党内监督条例》。集中学习由纪委书记刘静艳主持，纪委委员结合自身岗位情况","cover":"http://news.djtu.edu.cn/_uploadimg/image/20161216/2016121617360307307.jpg","from":"纪委","id":106,"time":"2016年12月16日","title":"我校全体纪委委员进行集中学习","type":"1","url":"http://news.djtu.edu.cn/list/detail.asp?m=3&id=60493"},{"content":"12月12日下午，我校2016年12月全国大学外语考试考务协调及动员会视频会议在沙河口校区和旅顺口校区召开。副校长关天民、教务处、党委宣传部、监察审计处","cover":"http://news.djtu.edu.cn/_uploadimg/image/20161214/20161214180970817081.jpg","from":"教务处","id":107,"time":"2016年12月14日","title":"我校召开2016年12月全国大学外语考试考务协调及动员会","type":"1","url":"http://news.djtu.edu.cn/list/detail.asp?m=3&id=60490"},{"content":"12月8日，市委高校工委组织召开大连高校纪念\u201c一二\u2022九\u201d运动81周年大会，集中表彰我市高校优秀集体和个人。我校环境与化学工程学院张巧丽同学从来自全市的22名候选人中脱颖而出，当选大连市大学生标兵。 ","cover":"http://news.djtu.edu.cn/_uploadimg/image/20161212/20161212171480648064.png","from":"学生处","id":108,"time":"2016年12月12日","title":"我校学生张巧丽当选大连市大学生标兵","type":"1","url":"http://news.djtu.edu.cn/list/detail.asp?m=3&id=60489"}]
     * type : 1
     */

    private String type;
    private List<NnlistBean> nnlist;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<NnlistBean> getNnlist() {
        return nnlist;
    }

    public void setNnlist(List<NnlistBean> nnlist) {
        this.nnlist = nnlist;
    }

    public static class NnlistBean {
        /**
         * content : 为深入学习贯彻党的十八届六中全会精神，12月14日下午，我校在图书馆报告厅举行专题培训，由校党委书记
         * cover : http://news.djtu.edu.cn/_uploadimg/image/20161223/201612231706001111.jpg
         * from : 党政办公室
         * id : 101
         * time : 2016年12月19日
         * title : 我校举办学习贯彻十八届六中全会精神干部培训班
         * type : 1
         * url : http://news.djtu.edu.cn/list/detail.asp?m=3&id=60495
         */

        private String content;
        private String cover;
        private String from;
        private int id;
        private String time;
        private String title;
        private String type;
        private String url;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
