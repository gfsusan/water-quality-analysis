package com.example.susan.myapplication;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Susan on 2018-01-31.
 */

public class ListviewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<DataView> dataViewList = new ArrayList<>();

    // ListviewAdapter의 생성자
    public ListviewAdapter () {

   }

   // Adapter에 사용되는 데이터의 개수를 리턴.
    @Override
    public int getCount() {
        return dataViewList.size();
    }

   // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "data_layout" Layout을 inflate하여 convertView 참조 획득
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.data_layout, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획슥
        TextView descriptionTV = (TextView)convertView.findViewById(R.id.description);
        TextView constraintsTV = (TextView)convertView.findViewById(R.id.constraints);
        TextView valueTV = (TextView)convertView.findViewById(R.id.value);
        TextView flagTV = (TextView)convertView.findViewById(R.id.flag);

        // Data Set(dataViewList)에서 position에 위치한 데이터 참조 획득
        DataView dataView = dataViewList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        descriptionTV.setText(dataView.getDescriptionText());
        constraintsTV.setText(dataView.getConstraintsText());
        valueTV.setText(dataView.getValueText());
        flagTV.setText(dataView.getFlagText());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 지정한 위치(position)에 있는 데이터 리턴: 필수 구현
    @Override
    public DataView getItem(int position) {
        return dataViewList.get(position);
    }

    // 아이템 데이터 추가를 위한 함수.
    public void addItem(String description, String constraints, String value, String flag) {
        DataView dv = new DataView();

        dv.setDescriptionText(description);
        dv.setConstraintsText(constraints);
        dv.setValueText(value);
        dv.setFlagTV(flag);

        dataViewList.add(dv);
    }

    public void addItem(String[] items) {
        DataView dv = new DataView();
        dv.setDescriptionText(items[0]);
        dv.setConstraintsText(items[1]);
        dv.setValueText(items[2]);
        dv.setFlagTV(items[3]);

        dataViewList.add(dv);
    }
}
