package com.example.susan.myapplication;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Display1Activity extends AppCompatActivity {
    ImageButton buttonBack;
    Button buttonMove;
    Spinner spinner1, spinner2, spinner3, spinner4;
    ArrayAdapter<String> defaultAdapter;
    TableLayout tableLayout;
    LinearLayout verticalLayout;

    final String nothing = "선택";
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display1);
        buttonBack = (ImageButton)findViewById(R.id.buttonBack);
        buttonMove = (Button)findViewById(R.id.buttonMove);

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);
        spinner4 = (Spinner)findViewById(R.id.spinner4);

        tableLayout = (TableLayout)findViewById(R.id.tableLayout);
        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        // defaultAdapter 생성
        defaultAdapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                new String[]{nothing});

        // set defaultAdapter
        spinner2.setAdapter(defaultAdapter);
        spinner3.setAdapter(defaultAdapter);
        spinner4.setAdapter(defaultAdapter);

        // On create, spinner1의 list를 받아온다.
        // DB에서 String 배열 받아온다 !!!!
        // String[] items = 어쩌고저쩌고
        String[] spinnerList1 = {"가져온 a1", "가져온 a2", "가져온 a3", "가져온 a4"};
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1,spinnerList1);
        // 선택되지 않았을 때 값 추가
        list1.add(0, nothing);

        // spinner1에게 arrayAdapter 줭
        spinner1.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                list1));


        // spinner1의 item이 선택되었을 때
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // 선택되면 DB에서 데이터 불러와서 spinner2에 item list 보여줌
                if(spinner1.getSelectedItemPosition()==0) {
                    spinner2.setAdapter(defaultAdapter);
                    spinner3.setAdapter(defaultAdapter);
                    spinner4.setAdapter(defaultAdapter);
                }
                else {
                    String[] spinnerList2 = {"가져온 b1", "가져온 b2", "가져온 b3", "가져온 b4"};
                    ArrayList<String> list2 = new ArrayList<>();
                    Collections.addAll(list2, spinnerList2);
                    // 선택되지 않았을 때 값 추가
                    list2.add(0, nothing);

                    spinner2.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.support_simple_spinner_dropdown_item,
                            list2));

                    //하위 스피너 초기화
                    spinner3.setAdapter(defaultAdapter);
                    spinner4.setAdapter(defaultAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // spinner2의 item이 선택되었을 때
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // 선택되면 DB에서 데이터 불러와서 spinner2에 item list 보여줌
                if(spinner2.getSelectedItemPosition() == 0) {
                    spinner3.setAdapter(defaultAdapter);
                    spinner4.setAdapter(defaultAdapter);
                }
                else {
                    String[] spinnerList3 = {"가져온 c1", "가져온 c2", "가져온 c3", "가져온 c4"};
                    ArrayList<String> list3 = new ArrayList<>();
                    Collections.addAll(list3, spinnerList3);
                    // 선택되지 않았을 때 값 추가
                    list3.add(0, nothing);

                    spinner3.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.support_simple_spinner_dropdown_item,
                            list3));

                    // 하위 스피너 초기화
                    spinner4.setAdapter(defaultAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // spinner3의 item이 선택되었을 때
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // 선택되면 DB에서 데이터 불러와서 spinner2에 item list 보여줌
                if(spinner3.getSelectedItemPosition() == 0) {
                    spinner4.setAdapter(defaultAdapter);
                }
                else {
                    String[] spinnerList4 = {"가져온 d1", "가져온 d2", "가져온 d3", "가져온 d4"};
                    ArrayList<String> list4 = new ArrayList<>();
                    Collections.addAll(list4, spinnerList4);
                    // 선택되지 않았을 때 값 추가
                    list4.add(0, nothing);

                    spinner4.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.support_simple_spinner_dropdown_item,
                            list4));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // spinner4의 item이 선택되었을 때
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // 선택되면 화면중앙부분 text set
                if(spinner4.getSelectedItemPosition() != 0) {
                    // 해당 station의 datapoint 개수를 받아온다.
                    int nDataPoint = 5;
                    double c1 = 5;
                    double c2 = 10;
                    double val = 7;
                    boolean flag = (val >=c1 && val <= c2);
                    String unit = "cm";

                    // datapoint의 개수만큼 tableRow 생성
                    for (int j = 0; j < nDataPoint; j++) {
                        TableRow tr = new TableRow(getApplicationContext());
                        //LinearLayout hl = new LinearLayout(getApplicationContext());
                        tr.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        //hl.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        TextView textview1 = new TextView(getApplicationContext());
                        textview1.setText("C["+j+"].Desc");
                        textview1.setTextColor(Color.BLUE);
                        textview1.setTextSize(20);
                        textview1.setWidth(300);
                        textview1.setHeight(300);
                        textview1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                        LinearLayout vl = new LinearLayout(getApplicationContext());
                        vl.setOrientation(LinearLayout.VERTICAL);
                        TextView textview2_1 = new TextView(getApplicationContext());
                        textview2_1.setText("C"+j+".val1~C"+j+".val2");
                        textview2_1.setTextSize(16);
                        textview2_1.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                        TextView textview2_2 = new TextView(getApplicationContext());
                        textview2_2.setText(val + " " + unit);
                        textview2_2.setTextSize(20);
                        textview2_2.setWidth(500);
                        textview2_2.setHeight(300);
                        textview2_2.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                        vl.addView(textview2_1);
                        vl.addView(textview2_2);

                        TextView textview3 = new TextView(getApplicationContext());
                        if(flag)
                            textview3.setText("O");
                        else
                            textview3.setText("X");
                        textview3.setTextSize(30);
                        textview3.setWidth(300);
                        textview3.setHeight(300);
                        textview3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        tr.addView(textview1);
                        tr.addView(vl);
                        tr.addView(textview3);
                        tableLayout.addView(tr);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonMove.setOnClickListener(new View.OnClickListener() {
            // '이동' 버튼 누르면 다음화면으로 이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Display2Activity.class);
                intent.putExtra("location1", spinner1.getSelectedItem().toString());
                intent.putExtra("location2", spinner2.getSelectedItem().toString());
                intent.putExtra("location3", spinner3.getSelectedItem().toString());
                intent.putExtra("location4", spinner4.getSelectedItem().toString());
                startActivity(intent);
            }
        });

    }
}
