package com.example.gw.traffic.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gw.traffic.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gw on 2019/3/1.
 */

public class IlluminationFragment extends SimpleFragment {

    public static Fragment newInstance() {
        return new IlluminationFragment();
    }

    private LineChart mChart;
    private TextView tvTitle;
    List<Integer> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_simple_line, container, false);
        mChart = (LineChart) v.findViewById(R.id.lineChart);
        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        tvTitle.setText("光照");
        initLineChart();
        return v;
    }


    private void initLineChart() {
        //模拟数据
        //1.设置x轴和y轴的点
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 16));
        entries.add(new Entry(2, 18));
        entries.add(new Entry(3, 19));
        entries.add(new Entry(4, 19));
        entries.add(new Entry(5, 19));
        entries.add(new Entry(6, 20));
        entries.add(new Entry(7, 22));
        entries.add(new Entry(8, 21));
        LineDataSet dataSet = new LineDataSet(entries, "");
        //得到X轴
        XAxis xAxis = mChart.getXAxis();
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //不显示网格线
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf((int) value).concat("号");
            }
        });
        //得到Y轴
        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setStartAtZero(true);
        YAxis rightYAxis = mChart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
        //设置y轴坐标之间的最小间隔
        //显示网格线
        yAxis.setDrawGridLines(true);
        //设置Y轴坐标之间的最小间隔
        yAxis.setGranularity(2f);
        //设置从Y轴值
        yAxis.setAxisMinimum(14);
        yAxis.setAxisMaximum(26);
        //隐藏x轴描述
        Description description = new Description();
        description.setEnabled(false);
        mChart.setDescription(description);
        LineData lineData = new LineData(dataSet);
        //是否绘制线条上的文字
        lineData.setDrawValues(false);
        mChart.setData(lineData);
        mChart.invalidate();
    }
}
