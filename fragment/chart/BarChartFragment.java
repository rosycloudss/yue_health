package com.yue_health.fragment.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.yue_health.R;
import com.yue_health.activity.MainActivity;
import com.yue_health.dao.PhysicalExaminationProjectDao;
import com.yue_health.dao.UserPhyExamInfoDao;
import com.yue_health.entity.PhysicalExaminationProject;
import com.yue_health.entity.UserPhysicalExaminationInfo;
import com.yue_health.fragment.BaseFragment;
import com.yue_health.valueFormatter.MyYAxisValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BarChartFragment extends BaseChartFragment {


    private static final String TAG = "BarChartFragment";

    private BarChart mBarChart;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.barchart_fragment, container, false);
        }
        initView();
        initEvents();
        initDatas();

        if (upeInfos.size() < 20) {
            showBarChart(mBarChart, getBarData(upeInfos.size(), 100));
        } else {
            showBarChart(mBarChart, getBarData(upeInfos.size(), 100));
        }

        return view;
    }

    @Override
    public void initView() {
        mBarChart = view.findViewById(R.id.id_barchart);
    }

    @Override
    public void initEvents() {

    }

    /**
     * 显示柱状图
     *
     * @param barChart
     * @param barData
     */
    private void showBarChart(BarChart barChart, BarData barData) {
        // 如果没有数据的时候，会显示这个，类似ListView的EmptyView
        barChart.setNoDataTextDescription("You need to provide data for the chart.");

        barChart.setData(barData); // 设置数据

        barChart.setDrawBorders(false); //是否在折线图上添加边框

//        String decription = pep.getName() + " " + pep.getUnit();
        barChart.setDescription("");// 数据描述
//        barChart.setDescriptionPosition(150,0);//数据描述的位置
//        barChart.setDescriptionColor(Color.RED);//数据的颜色
//        barChart.setDescriptionTextSize(40);//数据字体大小

        barChart.setDrawGridBackground(false); // 是否显示表格颜色
        barChart.setGridBackgroundColor(Color.RED); // 表格的的颜色
        //barChart.setBackgroundColor(Color.BLACK);// 设置整个图标控件的背景
        barChart.setDrawBarShadow(false);//柱状图没有数据的部分是否显示阴影效果

        barChart.setTouchEnabled(false); // 设置是否可以触摸
        barChart.setDragEnabled(false);// 是否可以拖拽
        barChart.setScaleEnabled(false);// 是否可以缩放
        barChart.setPinchZoom(false);//y轴的值是否跟随图表变换缩放;如果禁止，y轴的值会跟随图表变换缩放

        barChart.setDrawValueAboveBar(true);//柱状图上面的数值显示在柱子上面还是柱子里面

        barChart.getXAxis().setDrawGridLines(true);//是否显示竖直标尺线
        barChart.getXAxis().setLabelsToSkip(5);//设置横坐标显示的间隔
//        barChart.getXAxis().setLabelRotationAngle(20);//设置横坐标倾斜角度
//        barChart.getXAxis().setSpaceBetweenLabels(50);
        barChart.getXAxis().setDrawLabels(true);//是否显示X轴数值
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置 默认在上方

        barChart.getAxisRight().setDrawLabels(true);//右侧是否显示Y轴数值
        barChart.getAxisRight().setEnabled(false);//是否显示最右侧竖线
        barChart.getAxisRight().setDrawAxisLine(true);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getXAxis().setDrawAxisLine(true);


        YAxisValueFormatter custom = new MyYAxisValueFormatter(pep.getUnit());//自定义Y轴文字样式
        barChart.getAxisLeft().setValueFormatter(custom);

        barChart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART);//设置比例图标的位置
        barChart.getLegend().setDirection(Legend.LegendDirection.RIGHT_TO_LEFT);//设置比例图标和文字之间的位置方向
        barChart.getLegend().setTextColor(Color.RED);

        barChart.animateXY(1000, 1000);


    }

    /**
     * 用来处理,数据的方法
     */
    private BarData getBarData(int count, float range) {
        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xValues.add(upeInfos.get(i).getExamTime() + "");
        }

        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();

        for (int i = 0; i < count; i++) {
            float value = upeInfos.get(i).getData();
            yValues.add(new BarEntry(value, i));
        }

        // y轴的数据集合
        BarDataSet barDataSet = new BarDataSet(yValues, pep.getName());

        ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
        barDataSets.add(barDataSet); // add the datasets

        BarData barData = new BarData(xValues, barDataSet);

        return barData;
    }


}


