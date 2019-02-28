package com.example.gw.traffic.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gw.traffic.R;
import com.example.gw.traffic.fragment.DisplayFragment;
import com.example.gw.traffic.fragment.EnvironmentFragment;
import com.example.gw.traffic.fragment.MyAccountFragment;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    //侧滑菜单的布局
    private DrawerLayout drawerLayout;
    private TextView tvTitle;
    //标题栏
    private Toolbar toolbar;
    private String[] items = {"我的账户", "环境指标", "实时显示"};
    //侧滑菜单的内容项
    private ListView listView;
    private MyAdapter adapter;
    //切换的页面
    private MyAccountFragment f1;
    private DisplayFragment f3;
    private EnvironmentFragment f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(items[0]);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        adapter.setSelectedItem(0);//默认选中第一个item
        f1 = new MyAccountFragment();
        mContent = f1;//默认显示fragment1
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content_frame, f1);
        ft.commit();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");//设置Toolbar标题
        setSupportActionBar(toolbar);
        //创建返回键，并实现打开关/闭监听
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerToggle.syncState();//将左上角的图标和侧滑监听进行联动 达到动画效果显示
        drawerLayout.addDrawerListener(drawerToggle);//设置侧滑菜单的监听
    }


    /**
     * 修改显示的内容 不会重新加载
     **/
    private Fragment mContent;

    public void switchContent(Fragment to) {
        if (mContent != to) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(mContent).add(R.id.content_frame, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(mContent).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            mContent = to;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        switch (position) {
            case 0:
                if (f1 == null) {
                    f1 = new MyAccountFragment();
                }
                switchContent(f1);
                tvTitle.setText(items[0]);
                adapter.setSelectedItem(0);
                break;
            case 1:
                if (f2 == null) {
                    f2 = new EnvironmentFragment();
                }
                switchContent(f2);
                tvTitle.setText(items[1]);
                adapter.setSelectedItem(1);
                break;
            case 2:
                if (f3 == null) {
                    f3 = new DisplayFragment();
                }
                switchContent(f3);
                tvTitle.setText(items[2]);
                adapter.setSelectedItem(2);
                break;
        }
        drawerLayout.closeDrawers();//自动关闭侧滑菜单
        adapter.notifyDataSetChanged();
    }

    private class MyAdapter extends BaseAdapter {

        private int selestedItemPos = -1;

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private void setSelectedItem(int positon) {
            this.selestedItemPos = positon;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent, false);
                holder = new ViewHolder();
                holder.tvMenu = (TextView) convertView.findViewById(R.id.tvMenu);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvMenu.setText(items[position]);
            //选中设置字体为高亮显示
            if (selestedItemPos == position) {
                holder.tvMenu.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));
            } else {
                holder.tvMenu.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));
            }
            return convertView;
        }

        class ViewHolder {
            TextView tvMenu;
        }
    }
}
