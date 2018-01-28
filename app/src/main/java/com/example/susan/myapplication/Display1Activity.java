package com.example.susan.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class Display1Activity extends AppCompatActivity {
    Button buttonMove;
    Spinner spinner1, spinner2, spinner3, spinner4;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display1);
        buttonMove = (Button)findViewById(R.id.buttonMove);
        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);
        spinner4 = (Spinner)findViewById(R.id.spinner4);

        // On create, spinner1의 list를 받아온다.
        // DB에서 String 배열 받아온다 !!!!
        // String[] items = 어쩌고저쩌고
        final Context context = this;

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // 선택되면 DB에서 데이터 불러와서 spinner2에 item list 보여줌
                String[] items = {"가져온 a1", "가져온 a2", "가져온 a3", "가져온 a4"};
                arrayAdapter = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, items);
                // arrayAdapter.createFromResource(this, items, R.layout.support_simple_spinner_dropdown_item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // 선택되면 DB에서 데이터 불러와서 spinner2에 item list 보여줌
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // 선택되면 DB에서 데이터 불러와서 spinner2에 item list 보여줌
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // 선택되면 화면중앙에 해당하는 station의 최신정보를 받아와 criteria별로 표시함
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonMove.setOnClickListener(new View.OnClickListener() {
            // '이동' 버튼 누르면 다음화면으로 이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Display2Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
