package com.yue_health.fragment.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.yue_health.R;
import com.yue_health.fragment.BaseFragment;
import com.yue_health.valueFormatter.MyYAxisValueFormatter;

import java.util.ArrayList;

public class LineChartFragment extends BaseChartFragment {

    private static final String TAG = "LineChartFragment";
    private LineChart mLineChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.linechart_fragment, container, false);
        }
        initView();
        initEvents();
        initDatas();

        if (upeInfos.size() < 20) {
            showLineChart(mLineChart, getLineData(upeInfos.size(), 100));
        } else {
            showLineChart(mLineChart, getLineData(upeInfos.size(), 100));
        }

        return view;
    }

    @Override
    public void initView() {
        mLineChart = view.findViewById(R.id.id_linechart);
    }

    @Override
    public void initEvents() {

    }

    private void showLineChart(LineChart mLineChart, LineData lineData) {
// 如果没有数据的时候，会显示这个，类似ListView的EmptyView
        mLineChart.setNoDataTextDescription("You need to provide data for the chart.");

        mLineChart.setData(lineData); // 设置数据

        mLineChart.setDrawBorders(false); //是否在折线图上添加边框

//        String decription = pep.getName() + " " + pep.getUnit();
        mLineChart.setDescription("");// 数据描述
//        barChart.setDescriptionPosition(150,0);//数据描述的位置
//        barChart.setDescriptionColor(Color.RED);//数据的颜色
//        barChart.setDescriptionTextSize(40);//数据字体大小

        mLineChart.setDrawGridBackground(false); // 是否显示表格颜色
        mLineChart.setGridBackgroundColor(Color.RED); // 表格的的颜色
        //barChart.setBackgroundColor(Color.BLACK);// 设置整个图标控件的背景
//        mLineChart.setDrawBarShadow(false);//柱状图没有数据的部分是否显示阴影效果


        mLineChart.setTouchEnabled(false); // 设置是否可以触摸
        mLineChart.setDragEnabled(false);// 是否可以拖拽
        mLineChart.setScaleEnabled(false);// 是否可以缩放
        mLineChart.setPinchZoom(false);//y轴的值是否跟随图表变换缩放;如果禁止，y轴的值会跟随图表变换缩放
//        mLineChart.setDrawValueAboveBar(true);//柱状图上面的数值显示在柱子上面还是柱子里面

        mLineChart.getXAxis().setDrawGridLines(true);//是否显示竖直标尺线
        mLineChart.getXAxis().setLabelsToSkip(5);//设置横坐标显示的间隔
//        barChart.getXAxis().setLabelRotationAngle(20);//设置横坐标倾斜角度
//        barChart.getXAxis().setSpaceBetweenLabels(50);
        mLineChart.getXAxis().setDrawLabels(true);//是否显示X轴数值
        mLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置 默认在上方

        mLineChart.getAxisRight().setDrawLabels(true);//右侧是否显示Y轴数值
        mLineChart.getAxisRight().setEnabled(false);//是否显示最右侧竖线
        mLineChart.getAxisRight().setDrawAxisLine(true);
        mLineChart.getAxisLeft().setDrawAxisLine(false);
        mLineChart.getXAxis().setDrawAxisLine(true);


        YAxisValueFormatter custom = new MyYAxisValueFormatter(pep.getUnit());//自定义Y轴文字样式
        mLineChart.getAxisLeft().setValueFormatter(custom);

        mLineChart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART);//设置比例图标的位置
        mLineChart.getLegend().setDirection(Legend.LegendDirection.RIGHT_TO_LEFT);//设置比例图标和文字之间的位置方向
        mLineChart.getLegend().setTextColor(Color.RED);

        mLineChart.animateXY(1000, 1000);
    }

    private LineData getLineData(int count, float range) {
        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xValues.add(upeInfos.get(i).getExamTime() + "");
        }

        ArrayList<Entry> yValues = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
//            float value = (float) (Math.random() * range/*100以内的随机数*/) + 3;
            float value = upeInfos.get(i).getData();
            yValues.add(new BarEntry(value, i));
        }

        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, pep.getName());

        ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();
//        barDataSets.add(barDataSet); // add the datasets
        lineDataSets.add(lineDataSet);
        LineData lineData = new LineData(xValues, lineDataSet);

        return lineData;
    }


}
