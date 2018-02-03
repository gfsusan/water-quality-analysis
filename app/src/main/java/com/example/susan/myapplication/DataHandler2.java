package com.example.susan.myapplication;

import com.jjoe64.graphview.series.DataPoint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by susan on 2018-02-03.
 */

public class DataHandler2 {
    private ArrayList<String[]> locationData;
    private SimpleDateFormat dateFormat;

    DataHandler2(String[] data) {
        this.locationData = new ArrayList<>();
        this.dateFormat = new SimpleDateFormat("yyMMdd-hh:mm");

        for (int i = 0; i< data.length; i++ ) {
            locationData.add(data[i].split("@"));
        }
    }

    public ArrayList<String> getCriterias() {
        ArrayList<String> a = new ArrayList<>();
        HashSet<String> b = new HashSet<>();
        for (String[] line:locationData) {
            if(!a.contains(line[14]))
                a.add(line[14]);
        }
        a.add(0, "선택");
        return a;
    }

    DataPoint[] getDataPointsBetween(Date startDate, Date endDate, String criteria) {
        // criteria에 해당하는 모든 데이터의 id 갖고오기
        ArrayList<Integer> dataIDs = getCriteriaDataIDs(criteria);
        DataPoint[] dataPoints = new DataPoint[dataIDs.size()];
//        DataPoint[] dataPoints = new DataPoint[dataIDs.size() + 2];

        int ptr = 0;
        // endDate의 데이터를 포함해야하므로 endDate에 하루 추가
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.add(Calendar.DATE, 1);
        endDate = c.getTime();

        if(dataIDs.size()==0)
            return new DataPoint[0];

        for (int i:dataIDs) {
            try {
                Date date = dateFormat.parse(locationData.get(i)[8]);
                if (!startDate.after(date) && date.before(endDate)){
                    dataPoints[ptr] = makeDataPoint(i);
                    ptr++;
                }
            } catch (ParseException e)
            {
                e.printStackTrace();
            }

        }
        return dataPoints;
    }

    // criteria에 해당하는 모든 데이터 id 가지고오기
    private ArrayList<Integer> getCriteriaDataIDs (String criteria) {
        ArrayList<Integer> dataIDs = new ArrayList<>();
        for (int i = 0; i < locationData.size(); i++) {
            if(locationData.get(i)[14].equals(criteria)){
                dataIDs.add(i);
            }
        }

        return dataIDs;
    }

    private DataPoint makeDataPoint(int i) {
        // i번째 line의 데이터를 가지고 DataPoint를 만든다.
        Date date;
        DataPoint dataPoint= new DataPoint(1,1);
        try {
            date = dateFormat.parse(locationData.get(i)[8]);
            dataPoint = new DataPoint(date, Double.parseDouble(locationData.get(i)[9]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataPoint;
    }
}
