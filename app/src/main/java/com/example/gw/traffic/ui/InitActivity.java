package com.example.gw.traffic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.gw.traffic.R;

/**
 * Created by gw on 2019/2/28.
 */

public class InitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        //判断是否是第一次进入
//        if (TextUtils.isEmpty(DBUtil.getValue(this, ConfigKeys.isFirst))) {
//            DBUtil.setValue(this, ConfigKeys.isFirst, "1");
        //引导页延迟3秒进入
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(InitActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
//        } else {
//            Intent intent = new Intent(InitActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }
}
