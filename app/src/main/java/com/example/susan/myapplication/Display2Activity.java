package com.example.susan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.Date;

public class Display2Activity extends AppCompatActivity {
    ImageButton buttonBack;
    Button buttonMove;
    Spinner spinner1, datePicker1, datePicker2;
    GraphView graphView;
    LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2);
        buttonBack = (ImageButton)findViewById(R.id.buttonBack);
        buttonMove = (Button)findViewById(R.id.buttonMove);
        spinner1 = (Spinner)findViewById(R.id.spinner1);
        datePicker1 = (Spinner)findViewById(R.id.datePicker1);
        datePicker2 = (Spinner)findViewById(R.id.datePicker2);

        graphView = (GraphView)findViewById(R.id.graph);
        graphView.setTitle("기간별 조회");
        graphView.setTitleTextSize(64);


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonMove.setOnClickListener(new View.OnClickListener(){
        // '이동' 버튼 누르면 그래프 생성
            @Override
            public void onClick(View v){
                ;
            }
        });


        // x axis: date인 graphview
        // 데이터
        Calendar calendar = Calendar.getInstance();
        Date d1 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d2 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d3 = calendar.getTime();

        //series.appendData 도 있음
        series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(d1, 1), new DataPoint(d2, 5), new DataPoint(d3, 3)
        });

        graphView.addSeries(series);

        // set date label formatter
        graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graphView.getGridLabelRenderer().setNumHorizontalLabels(3);

        // graphView x bound 세우기
        graphView.getViewport().setMinX(d1.getTime());
        graphView.getViewport().setMaxX(d3.getTime());
        graphView.getViewport().setXAxisBoundsManual(true);



    }
}
