package com.function.luo.utils;

import android.util.Log;
import android.widget.Toast;

import com.function.luo.bean.DbBean;
import com.function.luo.bean.Student;
import com.function.luo.bean.User;
import com.function.luo.ormlitedeomo.MyApplication;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by luo on 2019/6/21.
 * 统一封装
 */

public class DaoUtils {

    /**
     * 根据数据表实体查询到的Dao
     * 第一个泛型为数据表实体
     * 第二个泛型为数据表实体中被指定为id的属性的数据类型
     */
    private static Dao<DbBean, String> mDao;


    /**
     * 单例模式获取实例
     * 必须写入对应的 Bean，每个都要手动写
     */
    public static Dao<DbBean, String> getInstance(DbBean dbBean) {
        if (null == mDao) {
            try {
                //必须单个实例去判断
                if (dbBean instanceof User) {
                    mDao = DatabaseHelper.getIntence().getDao(User.class);
                }else if (dbBean instanceof Student){
                    mDao = DatabaseHelper.getIntence().getDao(Student.class);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                Log.d("LUO","实例异常："+e.getMessage());
            }
        }
        return mDao;
    }



    /**
     * 插入单条数据
     *
     * @param dbBean
     */
    public static Boolean  insertData(DbBean dbBean) {
        try {
            //数据更新的行数返回值是 1.否则抛出异常

            int result = getInstance(dbBean).create(dbBean);
            if (result!= -1){
                Toast.makeText(MyApplication.getApplicationInstance(),"插入成功", LENGTH_SHORT).show();
                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("LUO","插入单行数据异常："+e.getMessage());
        }
        return false;
    }



    /**
     * 插入多条数据
     *
     * @param userList
     */
    public static Boolean insertListData(DbBean dbBean,List<DbBean> userList) {
        try {
            //数据更新的行数返回值是 1.否则抛出异常
            int result = getInstance(dbBean).create( userList);
            if (result!= -1){
                Toast.makeText(MyApplication.getApplicationInstance(),"插入成功", LENGTH_SHORT).show();

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("LUO","插入多行数据异常："+e.getMessage());
        }
        return false;
    }


    /**
     * 修改单条数据
     *
     * @param dbBean
     */
    public static Boolean updateData(DbBean dbBean) {
        try {
            //数据更新的行数返回值是 1.否则抛出异常
            int result = getInstance(dbBean).update(dbBean);
            if (result!= -1){
                Toast.makeText(MyApplication.getApplicationInstance(),"修改成功", LENGTH_SHORT).show();

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("LUO","修改单行数据异常："+e.getMessage());
        }
        return false;
    }




    /**
     * 查询表中所有数据
     *
     * @return
     */
    public static List<DbBean> queryData(DbBean dbBean) {
        List<DbBean> ormTables = null;
        try {
            ormTables = getInstance(dbBean).queryForAll();
            Log.d("LUO","======="+ormTables.size());
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("LUO","查询所有数据异常："+e.getMessage());
        }
        return ormTables;
    }


    /**
     * 自定义查询表中数据
     *  QueryBuilder<DbBean, String> builder = getInstance().queryBuilder();
     * Where<DbBean, String> where = builder.where();
     *  where.eq("_id", "111")
     * .and()
     * .eq("name", "aaa");
     */
    public static List<DbBean> queryByCustom(DbBean dbBean) {
        List<DbBean> ormTables = null;
        QueryBuilder<DbBean, String> builder = getInstance(dbBean).queryBuilder();
        Where<DbBean, String> where = builder.where();
        try {
            where.eq("_id", "1")
                    .and()
                    .eq("name", "zhy");
            ormTables = builder.query();

        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("LUO","查询符合条件数据异常："+e.getMessage());
        }
        return ormTables;
    }






    /**
     * 通过对象id进行数据删除
     *
     * @param dbBean
     */
    public static Boolean deleteByDbBean(DbBean dbBean) {
        try {
            int result = getInstance(dbBean).delete(dbBean);
            if (result!= -1){
                Toast.makeText(MyApplication.getApplicationInstance(),"删除成功", LENGTH_SHORT).show();

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("LUO","删除单行数据异常："+e.getMessage());
        }
        return false;
    }



    /**
     * 删除表中所有数据
     */
    public static Boolean deleteAllData(DbBean dbBean) {
        try {
            //数据更新的行数返回值是 1.否则抛出异常
            int result = getInstance(dbBean).deleteBuilder().delete();
            if (result!= -1){
                Toast.makeText(MyApplication.getApplicationInstance(),"删除全部成功", LENGTH_SHORT).show();

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("LUO","删除所有数据异常："+e.getMessage());

        }
        return false;
    }




}
