package com.example.susan.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
    Spinner spinner;
    EditText datePicker1, datePicker2;
    SimpleDateFormat dateFormat;
    String criteria;
    Date d1, d2;

    Button buttonMove;

    GraphView graphView;
    LineGraphSeries<DataPoint> series;
    GregorianCalendar calendar;

    String url = "http://165.194.35.103:8181/helloWeb/HServlet";
    DataHandler2 dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2);
        intent = getIntent();
        dh = new DataHandler2(intent.getStringArrayExtra("dataLines"));
        dateFormat = new SimpleDateFormat("yyyyMMdd");
        buttonBack = (ImageButton)findViewById(R.id.buttonBack);

        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                dh.getCriterias()));

        datePicker1 = (EditText) findViewById(R.id.datePicker1);
        datePicker2 = (EditText) findViewById(R.id.datePicker2);

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




        buttonMove.setOnClickListener(new View.OnClickListener(){
        // '이동' 버튼 누르면 그래프 생성
            @Override
            public void onClick(View v){
                criteria = spinner.getSelectedItem().toString();
                try {
                    d1 = dateFormat.parse(datePicker1.getText().toString());
                    d2 = dateFormat.parse(datePicker2.getText().toString());
                    System.out.println(d1);
                    System.out.println(d2);
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "날짜를 확인하세요",
                            Toast.LENGTH_SHORT);
                    return;
                }
                if(spinner.getSelectedItemId()==0) {
                    Toast.makeText(getApplicationContext(), "항목을 선택하세요",
                            Toast.LENGTH_SHORT);
                    return;
                } else {

                    //series.appendData 도 있음
                    series = new LineGraphSeries<DataPoint>(dh.getDataPointsBetween(d1, d2, criteria));

                    graphView.addSeries(series);

                    // set date label formatter
                    graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
                    graphView.getGridLabelRenderer().setNumHorizontalLabels(3);

                    // graphView x bound 세우기
                    graphView.getViewport().setMinX(d1.getTime());
                    graphView.getViewport().setMaxX(d2.getTime());
                    graphView.getViewport().setXAxisBoundsManual(true);
                }
            }
        });

    }


}