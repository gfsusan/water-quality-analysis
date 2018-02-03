package com.example.susan.myapplication;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Display1Activity extends AppCompatActivity {
    ImageButton buttonBack;
    Button buttonMove;
    Spinner spinner1, spinner2, spinner3, spinner4;
    ArrayAdapter<String> defaultAdapter;
    ListView listView;

    String url = "http://165.194.35.103:8181/helloWeb/HServlet";
    DataHandler dh;
    String location1, location2, location3;
    final String nothing = "선택";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display1);

        dh = new DataHandler(url, null);
        dh.execute();
//        ArrayList<String[]> data = dh.getData();
//            for (String[] a : data) {
//                for (String b : a) {
//                    System.out.print(b);
//                    Log.d(this.getClass().getName(), b);
//                }
//            }
        buttonBack = (ImageButton)findViewById(R.id.buttonBack);
        buttonMove = (Button)findViewById(R.id.buttonMove);

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);
        spinner4 = (Spinner)findViewById(R.id.spinner4);

        listView = (ListView)findViewById(R.id.listView);


        // defaultAdapter 생성
        defaultAdapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                new String[]{nothing});

        // set defaultAdapter
        spinner2.setAdapter(defaultAdapter);
        spinner3.setAdapter(defaultAdapter);
        spinner4.setAdapter(defaultAdapter);

        // spinner1에게 arrayAdapter 줭
        spinner1.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                dh.getNextLocationCandidate()));


        // spinner1의 item이 선택되었을 때
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // 선택되면 DB에서 데이터 불러와서 spinner2에 item list 보여줌
                location1 = spinner1.getSelectedItem().toString();
                if(spinner1.getSelectedItemPosition()==0) {
                    spinner2.setAdapter(defaultAdapter);
                    spinner3.setAdapter(defaultAdapter);
                    spinner4.setAdapter(defaultAdapter);
                }
                else {
                    spinner2.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.support_simple_spinner_dropdown_item,
                            dh.getNextLocationCandidate(location1)));

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
                location2 = spinner2.getSelectedItem().toString();
                if(spinner2.getSelectedItemPosition() == 0) {
                    spinner3.setAdapter(defaultAdapter);
                    spinner4.setAdapter(defaultAdapter);
                }
                else {
                    spinner3.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.support_simple_spinner_dropdown_item,
                            dh.getNextLocationCandidate(location1, location2)));

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
                location3 = spinner3.getSelectedItem().toString();
                if(spinner3.getSelectedItemPosition() == 0) {
                    spinner4.setAdapter(defaultAdapter);
                }
                else {
                    spinner4.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.support_simple_spinner_dropdown_item,
                            dh.getNextLocationCandidate(location1, location2, location3)));
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
                    // Adapter 생성
                    ListviewAdapter adapter = new ListviewAdapter();

                    // 리스트뷰 참조 및 Adapter 담기
                    listView = findViewById(R.id.listView);

                    for (int id: dh.getCriteriaDataIDs()) {
                        adapter.addItem(dh.getCriteriaData(id));
                    }

                    listView.setAdapter(adapter);
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
                if (spinner4.getSelectedItemId() != 0) {
                    Intent intent = new Intent(getApplicationContext(), Display2Activity.class);
                    intent.putExtra("dataLines", dh.getLocationData());
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "위치를 선택하세요", Toast.LENGTH_SHORT);
            }
        });

    }


}
