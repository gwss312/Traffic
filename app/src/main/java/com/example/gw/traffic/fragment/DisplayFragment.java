package com.example.gw.traffic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gw.traffic.R;

/**
 * Created by gw on 2019/2/28.
 */

public class DisplayFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, null);
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setOffscreenPageLimit(2);
        //viewpager适配器
        PageAdapter a = new PageAdapter(getActivity().getSupportFragmentManager());
        pager.setAdapter(a);
        return view;
    }

    private class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            Fragment f = null;

            switch (pos) {
                case 0:
                    f = TemperatureFragment.newInstance();
                    break;
                case 1:
                    f = DampnessFragment.newInstance();
                    break;
                case 2:
                    f = IlluminationFragment.newInstance();
                    break;
            }

            return f;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
