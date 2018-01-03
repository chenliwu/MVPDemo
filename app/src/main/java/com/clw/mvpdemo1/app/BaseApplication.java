package com.clw.mvpdemo1.app;

import android.app.Application;
import org.xutils.x;

public class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        init_xUtils();
    }

    /**
     * 初始化xUtils框架
     */
    void init_xUtils(){
        x.Ext.init(this);
    }
}
