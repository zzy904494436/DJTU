package com.qianfeng.mymac.djtu.entityclass2;

import java.util.List;

/**
 * Created by mymac on 2016/12/24.
 */

public class LostList {

    private List<GoodlistBean> goodlist;

    public List<GoodlistBean> getGoodlist() {
        return goodlist;
    }

    public void setGoodlist(List<GoodlistBean> goodlist) {
        this.goodlist = goodlist;
    }

    public static class GoodlistBean {
        /**
         * from : 刘同学
         * goods : 小米移动电源
         * id : 10101
         * place : 教学楼J544
         * tel : 15998531608
         * time : 2016.12.12
         */

        private String from;
        private String goods;
        private String id;
        private String place;
        private String tel;
        private String time;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getGoods() {
            return goods;
        }

        public void setGoods(String goods) {
            this.goods = goods;
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

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
