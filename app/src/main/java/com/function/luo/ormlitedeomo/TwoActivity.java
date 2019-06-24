package com.function.luo.ormlitedeomo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.function.luo.bean.DbBean;
import com.function.luo.bean.Student;
import com.function.luo.bean.User;
import com.function.luo.utils.DaoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luo on 2019/6/24.
 */

public class TwoActivity  extends AppCompatActivity {
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.button6)
    Button button6;
    @BindView(R.id.button7)
    Button button7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                //增加
                addUserData();
                break;
            case R.id.button2:
                //修改
                updateUserData();
                break;
            case R.id.button3:
                //删除
                deleteUserData();
                break;
            case R.id.button4:
                //全部查询
                queryListData();
                break;
            case R.id.button5:
                //按条件查询
                break;
            case R.id.button6:
                //sql 语句查询
                break;
            default:

        }
    }


    /**
     * 插入数据
     */
    public void addUserData() {

        Student u1 = new Student("zhy", "2B青年");
        DaoUtils.insertData(u1);
        u1 = new Student("zhy2", "2B青年");
        DaoUtils.insertData(u1);
        u1 = new Student("zhy3", "2B青年");
        DaoUtils.insertData(u1);
        u1 = new Student("zhy4", "2B青年");
        DaoUtils.insertData(u1);
        u1 = new Student("zhy5", "2B青年");
        DaoUtils.insertData(u1);
        u1 = new Student("zhy6", "2B青年");
        DaoUtils.insertData(u1);


    }


    /**
     * 删除数据
     */
    public void deleteUserData() {
        Student student = new Student();
        student.setId(2);
        DaoUtils.deleteByDbBean(student);
    }

    /**
     * 更新数据
     */
    public void updateUserData() {
        Student student = new Student("zhy-android", "2B青年");
        student.setId(3);
        DaoUtils.updateData(student);


    }


    /**
     * 查询数据
     */
    public void queryListData() {
        Student student = new Student();
        List<DbBean> dbBeans = DaoUtils.queryData(student);
        if (dbBeans != null) {
            Log.d("LUO", "=======" + dbBeans.size());
            tv1.setText(dbBeans.toString() + "=====" + dbBeans.size());
        } else {
            tv1.setText("0");
        }


    }

    public static void launch(Context mContext) {
        Intent intent = new Intent(mContext,TwoActivity.class);
        mContext.startActivity(intent);
    }
}
