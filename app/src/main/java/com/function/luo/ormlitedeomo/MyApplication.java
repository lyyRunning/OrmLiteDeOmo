package com.function.luo.ormlitedeomo;

import android.app.Application;
import android.content.Context;

import com.function.luo.utils.DatabaseHelper;

/**
 * Created by luo on 2019/6/24.
 */

public class MyApplication extends Application {

    public static Context mContex;
    @Override
    public void onCreate() {
        super.onCreate();
        mContex = this;
        initDataBase();

    }

    /**
     * 初始化本地数据库
     */
    private void initDataBase() {

        DatabaseHelper.initOrmLite(this);
    }



    public static Context getApplicationInstance(){
        return mContex;
    }
}
