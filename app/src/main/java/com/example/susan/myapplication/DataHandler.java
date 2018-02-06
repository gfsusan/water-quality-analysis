package com.example.susan.myapplication;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Susan on 2018-02-01.
 */

public class DataHandler  {
    private String[] dataLines;
    private ArrayList<String[]> data;
    private String url;
    private ContentValues values;
    // location추리는거
    private HashSet<Integer> location1IDs, location2IDs, location3IDs, location4IDs;


    public DataHandler(String s) {
//
//        String temp = "NJ-DS-CLI-01@나주 다시(배) 잔류염소@NJ-DS@금영섬@전남중부권@나주@남평@null@180123-00:20@0.45@0@CLI@0.8@0.5@잔류염소@PPM<br>NJ-NP-CLI-01@나주 남평(배) 잔류염소@NJ-NP@금영섬@전남중부권@나주@남평@d3@180123-12:34@0.46@0@CLI@0.8@0.5@잔류염소@PPM<br>NJ-NP-PHI-01@나주 남평(배) pH@NJ-NP@금영섬@전남중부권@나주@남평@d1@180123-12:34@7.11@0@PHI@7.5@6.5@PH@PH<br>NJ-NP-TBI-01@나주 남평(배) 탁도@NJ-NP@금영섬@전남중부권@나주@남평@d4@180123-12:34@0.45@0@TBI@0.5@-9999.0@탁도@NTU<br>NJ-NP-TEI-01@나주 남평(배) 온도@NJ-NP@금영섬@전남중부권@나주@남평@d2@180123-12:34@10.1@0@TEI@30.0@4.0@온도@℃<br>WD-ND-PHI-01@완도 노두(배) pH@WD-ND@금영성@전남서남권@완도@대야@null@null@0.0@0@PHI@7.5@6.5@PH@PH<br>WD-ND-TEI-01@완도 노두(배) 온도@WD-ND@금영성@전남서남권@완도@대야@null@null@0.0@0@TEI@30.0@4.0@온도@℃";
//        System.out.print(temp);
//        dataLines = temp.split(<br>");

        System.out.print(s);
        dataLines = s.split("<br>");
        data = new ArrayList<>();
        for (int i = 0; i < dataLines.length; i++) {
            data.add(dataLines[i].split("@"));
        }

        location1IDs = new HashSet<>();
        for (int i = 0; i < data.size(); i++)
            location1IDs.add(i);
    }
    public ArrayList<String> getNextLocationCandidate() {
        HashSet<String> hashSet = new HashSet<>();

        for (int i : location1IDs) {
            hashSet.add(data.get(i)[3]);
        }
        ArrayList<String> location1s = new ArrayList<>();
        location1s.add(0, "선택");
        location1s.addAll(hashSet);

        return location1s;
    }

    public ArrayList<String> getNextLocationCandidate(String location1) {
        HashSet<String> hashSet = new HashSet<>();
        location2IDs = new HashSet<>();
        for (int i : location1IDs) {
            if (data.get(i)[3].equals(location1)) {
                hashSet.add(data.get(i)[4]);
                location2IDs.add(i);
            }
        }
        ArrayList<String> location2s = new ArrayList<>();
        location2s.add(0, "선택");
        location2s.addAll(hashSet);

        return location2s;
    }

    public ArrayList<String> getNextLocationCandidate(String location1, String location2) {
        HashSet<String> hashSet = new HashSet<>();
        location3IDs = new HashSet<>();
        for (int i : location2IDs) {
            if (data.get(i)[3].equals(location1) && data.get(i)[4].equals(location2)) {
                hashSet.add(data.get(i)[5]);
                location3IDs.add(i);
            }
        }
        ArrayList<String> location3s = new ArrayList<>();
        location3s.add(0, "선택");
        location3s.addAll(hashSet);

        return location3s;
    }

    public ArrayList<String> getNextLocationCandidate(String location1, String location2, String location3) {
        HashSet<String> hashSet = new HashSet<>();
        location4IDs = new HashSet<>();
        for (int i : location3IDs) {
            if (data.get(i)[3].equals(location1) && data.get(i)[4].equals(location2) && data.get(i)[5].equals(location3)) {
                hashSet.add(data.get(i)[6]);
                location4IDs.add(i);
            }
        }
        ArrayList<String> location4s = new ArrayList<>();
        location4s.add(0, "선택");
        location4s.addAll(hashSet);

        return location4s;
    }

    public ArrayList<Integer> getCriteriaDataIDs() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        ArrayList<Integer> a = new ArrayList<>();
        for (int i : location4IDs) {
            hashMap.put(data.get(i)[14], i);
        }
        for (String key : hashMap.keySet()) {
            a.add(hashMap.get(key));
        }
        return a;
    }

    public String[] getCriteriaData(int i) {
        String[] result = new String[4];
        result[0] = data.get(i)[14];
        if (Double.parseDouble(data.get(i)[13]) == -9999.0)
            result[1] = "< " + data.get(i)[12];
        else
            result[1] = data.get(i)[13] + " ~ " + data.get(i)[12];
        result[2] = data.get(i)[9] + " " + data.get(i)[15];
        if (data.get(i)[10].equals("1")) {
            result[3] = "O";
        } else
            result[3] = "X";

        return result;
    }

    // criteria 불러오기 위해
    public HashSet<Integer> getLocation4IDs() {

        return location4IDs;
    }

    public String[] getLocationData() {
        String[] a = new String[location4IDs.size()];
        int ptr = 0;
        for (Integer i : location4IDs) {
            a[ptr] = dataLines[i];
            ptr++;
        }
        return a;
    }
}
