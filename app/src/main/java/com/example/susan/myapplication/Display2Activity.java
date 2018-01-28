package com.example.susan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Display2Activity extends AppCompatActivity {
    Button buttonMove;
    Spinner spinner1, spinner2, spinner3;
LineGraphSeries<DataPoint> series;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2);
        buttonMove = (Button)findViewById(R.id.buttonMove);
        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);

        buttonMove.setOnClickListener(new View.OnClickListener(){
        // '이동' 버튼 누르면 그래프 생성
            @Override
            public void onClick(View v){
                ;
            }
        });

        double y, x;
        x = -5.0;

        GraphView graph = (GraphView)findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        for(int i=0; i<500; i++){
            x= x+0.1;
            y= Math.sin(x);
            series.appendData(new DataPoint(x,y),true, 500);
        }
        graph.addSeries(series);
    }
}
