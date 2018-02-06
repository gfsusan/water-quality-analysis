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
