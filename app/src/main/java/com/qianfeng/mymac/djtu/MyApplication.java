package com.qianfeng.mymac.djtu;

import android.app.Application;
import android.util.Log;

import com.qianfeng.mymac.djtu.entityclass.User;

import org.xutils.x;

/**
 * Created by mymac on 2016/11/10.
 */

public class MyApplication extends Application {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;

    }

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(true);

        user = new User();
        user.setLoginstate(false);
        Log.i("state : ",user.getLoginstate()+":appliion:"+user.getLoginstate());
    }
}
