package com.qianfeng.mymac.djtu.entityclass2;

import java.util.List;

/**
 * Created by mymac on 2016/12/24.
 */

public class ContactList {

    /**
     * contactlist : [{"dep":"软件学院","id":1,"room":"院长室","tel":"86229555"},{"dep":"软件学院","id":2,"room":"书记室","tel":"86221401"},{"dep":"软件学院","id":3,"room":"副院长室","tel":"86220101"},{"dep":"软件学院","id":4,"room":"副院长室","tel":"86229632"},{"dep":"软件学院","id":5,"room":"副院长室","tel":"86224574"},{"dep":"软件学院","id":6,"room":"副书记室","tel":"86225632"},{"dep":"软件学院","id":7,"room":"办公室","tel":"86221212"},{"dep":"软件学院","id":8,"room":"办公室","tel":"86221210"},{"dep":"软件学院","id":9,"room":"辅导员","tel":"86221084"},{"dep":"软件学院","id":10,"room":"辅导员","tel":"86221489"},{"dep":"软件学院","id":11,"room":"辅导员","tel":"86227841"},{"dep":"软件学院","id":12,"room":"辅导员","tel":"86227896"},{"dep":"软件学院","id":13,"room":"软件教研室","tel":"86227874"},{"dep":"软件学院","id":14,"room":"网络教研室","tel":"86224587"},{"dep":"软件学院","id":15,"room":"测试教研室","tel":"86223357"},{"dep":"软件学院","id":16,"room":"计算机应用教研室","tel":"86223302"},{"dep":"软件学院","id":17,"room":"软件工程教研室","tel":"86223301"},{"dep":"理学院","id":18,"room":"院长室","tel":"85229610"},{"dep":"理学院","id":19,"room":"书记室","tel":"85220149"},{"dep":"理学院","id":20,"room":"副院长室","tel":"85221212"},{"dep":"理学院","id":21,"room":"副院长室","tel":"852236521"},{"dep":"理学院","id":22,"room":"副书记室","tel":"85223232"},{"dep":"理学院","id":23,"room":"办公室","tel":"85229696"},{"dep":"理学院","id":24,"room":"办公室","tel":"85226214"},{"dep":"理学院","id":25,"room":"办公室","tel":"85226547"},{"dep":"理学院","id":26,"room":"辅导员","tel":"85229696"},{"dep":"理学院","id":27,"room":"数学教研室","tel":"85441212"},{"dep":"理学院","id":28,"room":"数学教研室2","tel":"85331212"}]
     * dep : 全部
     */

    private String dep;
    private List<ContactlistBean> contactlist;

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public List<ContactlistBean> getContactlist() {
        return contactlist;
    }

    public void setContactlist(List<ContactlistBean> contactlist) {
        this.contactlist = contactlist;
    }

    public static class ContactlistBean {
        /**
         * dep : 软件学院
         * id : 1
         * room : 院长室
         * tel : 86229555
         */

        private String dep;
        private int id;
        private String room;
        private String tel;

        public String getDep() {
            return dep;
        }

        public void setDep(String dep) {
            this.dep = dep;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}
