package com.example.gw.traffic.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gw.traffic.R;
import com.example.gw.traffic.db.ConfigKeys;
import com.example.gw.traffic.db.DBUtil;

/**
 * Created by gw on 2019/2/28.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText etName, etPwd;
    private TextView tvSetting;
    private CheckBox ckSavePwd, ckAutoLogin;
    private Button btnLogin, btnRegister;
    private String name, pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
    }

    private void initUI() {
        etName = (EditText) findViewById(R.id.etName);
        etPwd = (EditText) findViewById(R.id.etPwd);
        tvSetting = (TextView) findViewById(R.id.tvSetting);
        ckSavePwd = (CheckBox) findViewById(R.id.ckSavePwd);
        ckAutoLogin = (CheckBox) findViewById(R.id.ckAutoLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvSetting.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        name = DBUtil.getValue(this, ConfigKeys.name);
        pwd = DBUtil.getValue(this, ConfigKeys.pwd);
        if (!TextUtils.isEmpty(name)) {
            etName.setText(name);
            etPwd.setText(pwd);
            ckSavePwd.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            //trim去空格
            name = etName.getText().toString().trim();
            pwd = etPwd.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(pwd)) {
                Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (ckSavePwd.isChecked()) {
                DBUtil.setValue(this, ConfigKeys.name, name);
                DBUtil.setValue(this, ConfigKeys.pwd, pwd);
            } else {
                DBUtil.setValue(this, ConfigKeys.name, "");
                DBUtil.setValue(this, ConfigKeys.pwd, "");
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (v == btnRegister) {
            Toast.makeText(this, "注册", Toast.LENGTH_SHORT).show();
        } else if (v == tvSetting) {
            showdialog();
        }
    }

    private void showdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this);
        builder.setTitle("");
        View view = LayoutInflater.from(this).inflate(
                R.layout.setting_dialog, null);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        final EditText etUrl = (EditText) view.findViewById(R.id.etUrl);
        final EditText etPort = (EditText) view.findViewById(R.id.etPort);
        if (!TextUtils.isEmpty(DBUtil.getValue(LoginActivity.this, ConfigKeys.url))) {
            etUrl.setText(DBUtil.getValue(LoginActivity.this, ConfigKeys.url));
            etPort.setText(DBUtil.getValue(LoginActivity.this, ConfigKeys.port));
        }
        Button btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = etUrl.getText().toString().trim();
                String port = etPort.getText().toString().trim();
                if (TextUtils.isEmpty(url)) {
                    Toast.makeText(LoginActivity.this, "服务器地址不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(port)) {
                    Toast.makeText(LoginActivity.this, "端口号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = Integer.parseInt(etPort.getText().toString());
                if (num < 0 || num > 65535) {
                    Toast.makeText(LoginActivity.this, "端口号不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                DBUtil.setValue(LoginActivity.this, ConfigKeys.url, url);
                DBUtil.setValue(LoginActivity.this, ConfigKeys.port, port);
                alertDialog.dismiss();
            }
        });
        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
