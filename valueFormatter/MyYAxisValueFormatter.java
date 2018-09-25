package com.yue_health.valueFormatter;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;

import java.text.DecimalFormat;

public class MyYAxisValueFormatter implements YAxisValueFormatter {

    private DecimalFormat mFormat;
    private String unit;//单位

    public MyYAxisValueFormatter(String unit) {
        mFormat = new DecimalFormat("###,###,###,##0");
        this.unit = unit;
    }

    @Override
    public String getFormattedValue(float value, YAxis yAxis) {
        String unit = "";
        if (this.unit != null) {
            unit = this.unit;
        }
        return mFormat.format(value) + unit;
    }
}
