package com.example.susan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Display2Activity extends AppCompatActivity {
    ImageButton buttonBack;
    Button buttonMove;
    Spinner spinner1, spinner2, spinner3;
    GraphView graphView;
    LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2);
        buttonBack = (ImageButton)findViewById(R.id.buttonBack);
        buttonMove = (Button)findViewById(R.id.buttonMove);
        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);

        graphView = (GraphView)findViewById(R.id.graph);
        graphView.setTitle("기간별 조회");
        graphView.setTitleTextSize(64);

//        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

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

        double y, x;
        x = -5.0;

        series = new LineGraphSeries<DataPoint>();
        for(int i=0; i<500; i++){
            x= x+0.1;
            y= Math.sin(x);
            series.appendData(new DataPoint(x,y),true, 500);
        }
        graphView.addSeries(series);
    }
}
