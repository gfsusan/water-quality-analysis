package com.example.susan.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Display2Activity extends AppCompatActivity {
    Intent intent;
    ImageButton buttonBack;
    Spinner spinner1;
    Button datePicker1, datePicker2;
    Button buttonMove;
    GraphView graphView;
    LineGraphSeries<DataPoint> series;
    GregorianCalendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2);
        intent = getIntent();

        buttonBack = (ImageButton)findViewById(R.id.buttonBack);

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        datePicker1 = (Button)findViewById(R.id.datePicker1);
        datePicker2 = (Button)findViewById(R.id.datePicker2);

        buttonMove = (Button)findViewById(R.id.buttonMove);

        graphView = (GraphView)findViewById(R.id.graph);
        graphView.setTitle("기간별 조회");
        graphView.setTitleTextSize(64);


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        // 날짜 선택
//        calendar = new GregorianCalendar();
//        datePicker1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                DatePickerDialog dialog = new DatePickerDialog(getApplicationContext(), listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
//                dialog.show();
//            }
//        });


        buttonMove.setOnClickListener(new View.OnClickListener(){
        // '이동' 버튼 누르면 그래프 생성
            @Override
            public void onClick(View v){
                // x axis: date인 graphview
                // 데이터
                Calendar calendar = Calendar.getInstance();
                Date d1 = calendar.getTime();
                calendar.add(Calendar.DATE, 1);
                Date d2 = calendar.getTime();
                calendar.add(Calendar.DATE, 1);
                Date d3 = calendar.getTime();

                String dateString = "180123-12:34";
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd-hh:mm");
                Date date = new Date();
                try{
                    date = dateFormat.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //series.appendData 도 있음
                series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                        new DataPoint(d1, 1), new DataPoint(d2, 5), new DataPoint(d3, 3), new DataPoint(date, 8)
                });
                graphView.addSeries(series);

                // set date label formatter
                graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
                graphView.getGridLabelRenderer().setNumHorizontalLabels(3);

                // graphView x bound 세우기
                graphView.getViewport().setMinX(date.getTime());
                graphView.getViewport().setMaxX(d3.getTime());
                graphView.getViewport().setXAxisBoundsManual(true);

                System.out.println(date);
                System.out.println(d1);
                System.out.println(d2);
                System.out.println(d3);

            }
        });

    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Toast.makeText(getApplicationContext(), i+"년"+(i1+1)+"월"+i2+"일", Toast.LENGTH_SHORT).show();
        }
    };

}