package com.function.luo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.function.luo.bean.Student;
import com.function.luo.bean.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;


/**
 * Created by luo on 2019/6/21.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    /**
     * 数据库名称
     */
    private static final String TABLE_NAME = "sqlite-test.db";
    /**
     * 版本号
     */
    private static final int DBVERSION = 2;

    private static Context mContext;
    /**
     * 单例
     */
    private static DatabaseHelper mInstance;


    private DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, DBVERSION);
    }

    /**
     * 初始化
     *
     * @param context
     */
    public static void initOrmLite(Context context) {
        mContext = context;
        getIntence();
    }

    /**
     * 创建数据库表
     *
     * @param database
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        try {
           TableUtils.createTable(connectionSource,  Student.class);
           TableUtils.createTable(connectionSource, User.class);

        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("LUO","插入单条数据异常："+e.getMessage());
        }
    }

    /**
     * 升级数据库
     *
     * @param database
     * @param connectionSource
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Student.class, true);

            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("LUO","插入多条数据异常："+e.getMessage());

        }
    }


    /**
     * 单例获取该Helper
     *
     * @param
     * @return
     */
    public static synchronized DatabaseHelper getIntence() {

        if (null == mInstance) {
            synchronized (DatabaseHelper.class) {
                if (null == mInstance) {
                    mInstance = new DatabaseHelper(mContext);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取数据对象
     *
     * @param classz 对应的表实体的字节码对象
     * @return Dao<T, ID> :T：表实体对象类型.ID：对应的表实体中被指定为id字段的属性类型
     * @throws SQLException
     */
    @Override
    public Dao getDao(Class classz) throws SQLException {
        return super.getDao(classz);
    }



}
