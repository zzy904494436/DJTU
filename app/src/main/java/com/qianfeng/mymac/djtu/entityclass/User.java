package com.qianfeng.mymac.djtu.entityclass;

/**
 * Created by mymac on 2016/11/10.
 */

public class User {
    private int snumber ;
    private String sname ;
    private String XUEYUAN;
    private String majorAndClass ;
    private String sphone;
    private Boolean loginstate;

    public int getSnumber() {
        return snumber;
    }

    public void setSnumber(int snumber) {
        this.snumber = snumber;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getXUEYUAN() {
        return XUEYUAN;
    }

    public void setXUEYUAN(String XUEYUAN) {
        this.XUEYUAN = XUEYUAN;
    }

    public String getMajorAndClass() {
        return majorAndClass;
    }

    public void setMajorAndClass(String majorAndClass) {
        this.majorAndClass = majorAndClass;
    }

    public Boolean getLoginstate() {
        return loginstate;
    }

    public void setLoginstate(Boolean loginstate) {
        this.loginstate = loginstate;
    }

}
