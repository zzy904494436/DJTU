package com.qianfeng.mymac.djtu.entityclass2;

/**
 * Created by mymac on 2016/12/24.
 */

public class GetLogin {

    /**
     * flag : 1
     * password : 1010
     * userInfo : {"password":"1010","stuCLASS":"计算","stuID":"1218130123","stuNAME":"战宁宁","stuTEL":"15998531608","stuYUAN":"里"}
     * username : 1218130123
     */

    private int flag;
    private String password;
    private UserInfoBean userInfo;
    private String username;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static class UserInfoBean {
        /**
         * password : 1010
         * stuCLASS : 计算
         * stuID : 1218130123
         * stuNAME : 战宁宁
         * stuTEL : 15998531608
         * stuYUAN : 里
         */

        private String password;
        private String stuCLASS;
        private String stuID;
        private String stuNAME;
        private String stuTEL;
        private String stuYUAN;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getStuCLASS() {
            return stuCLASS;
        }

        public void setStuCLASS(String stuCLASS) {
            this.stuCLASS = stuCLASS;
        }

        public String getStuID() {
            return stuID;
        }

        public void setStuID(String stuID) {
            this.stuID = stuID;
        }

        public String getStuNAME() {
            return stuNAME;
        }

        public void setStuNAME(String stuNAME) {
            this.stuNAME = stuNAME;
        }

        public String getStuTEL() {
            return stuTEL;
        }

        public void setStuTEL(String stuTEL) {
            this.stuTEL = stuTEL;
        }

        public String getStuYUAN() {
            return stuYUAN;
        }

        public void setStuYUAN(String stuYUAN) {
            this.stuYUAN = stuYUAN;
        }
    }
}
