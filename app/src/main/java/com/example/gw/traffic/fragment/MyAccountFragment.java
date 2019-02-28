package com.example.gw.traffic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.gw.traffic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gw on 2019/2/28.
 */

public class MyAccountFragment extends Fragment {
    private Spinner spCarNo;
    private ArrayAdapter<String> adapter;
    private List<String> list = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_account, null);
        spCarNo = (Spinner) view.findViewById(R.id.spCarNo);
        // 第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        list.add("    1    ");
        list.add("    2    ");
        list.add("    3    ");
        list.add("    4    ");
        // 第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        // 第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 第四步：将适配器添加到下拉列表上
        spCarNo.setAdapter(adapter);
        // 第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        spCarNo.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                        /* 将所选mySpinner 的值带入myTextView 中 */
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        return view;
    }
}
