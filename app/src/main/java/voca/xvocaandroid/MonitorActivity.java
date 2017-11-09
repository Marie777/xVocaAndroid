package voca.xvocaandroid;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MonitorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        LineChart mChart = findViewById(R.id.lcProgress);

        List<Entry> dataSet = new ArrayList<>();

        dataSet.add(new Entry(1509556495, 0));
        dataSet.add(new Entry(1509642895, 5));
        dataSet.add(new Entry(1509729295, 4));
        dataSet.add(new Entry(1509815695, 7));
        dataSet.add(new Entry(1509902095, 9));
        dataSet.add(new Entry(1509988495, 10));

        setData(dataSet, mChart);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setGranularity(86400);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            private SimpleDateFormat mFormatter = new SimpleDateFormat("dd MMM");
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mFormatter.format(new Date((int) value * 1000));
            }
        });

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        mChart.getAxisRight().setEnabled(false);

        mChart.getAxisLeft().setGranularity(1);
    }

    private void setData(List<Entry> dataSet, LineChart mChart) {
        LineDataSet set1 = new LineDataSet(dataSet, "Quiz scores");

        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(ColorTemplate.getHoloBlue());
        set1.setCircleColor(Color.WHITE);
        set1.setLineWidth(2f);
        set1.setCircleRadius(3f);
        set1.setFillAlpha(65);
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setHighLightColor(Color.rgb(244, 117, 117));

        set1.setCircleColor(Color.BLACK);
        //set1.setDrawCircleHole(false);
        //set1.setFillFormatter(new MyFillFormatter(0f));
        //set1.setDrawHorizontalHighlightIndicator(false);
        //set1.setVisible(false);
        //set1.setCircleHoleColor(Color.WHITE);

        // create a data object with the datasets
        LineData data = new LineData(set1);
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(9f);

        // set data
        mChart.setData(data);
    }

}
