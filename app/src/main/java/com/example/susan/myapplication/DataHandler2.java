package com.example.susan.myapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by susan on 2018-02-03.
 */

public class DataHandler2 {
    ArrayList<String[]> locationData;

    DataHandler2(String[] data) {
        this.locationData = new ArrayList<>();

        for (int i = 0; i< data.length; i++ ) {
            locationData.add(data[i].split("@"));
        }
    }



}
