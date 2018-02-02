package com.example.susan.myapplication;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Susan on 2018-02-01.
 */

public class DataHandler extends AsyncTask<Void, Void, String> {
    private String[][] data;
    private String url;
    private ContentValues values;
    // location추리는거
    private HashSet<Integer> location1IDs, location2IDs, location3IDs, location4IDs;


    public DataHandler(String url, ContentValues values) {

        this.url = url;
        this.values = values;

        String temp = "NJ-DS-CLI-01@나주 다시(배) 잔류염소@NJ-DS@금영섬@전남중부권@나주@남평@null@null@0.0@0@CLI@0.8@0.5@잔류염소@PPM<br>NJ-NP-CLI-01@나주 남평(배) 잔류염소@NJ-NP@금영섬@전남중부권@나주@남평@d3@180123-12:34@0.46@0@CLI@0.8@0.5@잔류염소@PPM<br>NJ-NP-PHI-01@나주 남평(배) pH@NJ-NP@금영섬@전남중부권@나주@남평@d1@180123-12:34@7.11@0@PHI@7.5@6.5@PH@PH<br>NJ-NP-TBI-01@나주 남평(배) 탁도@NJ-NP@금영섬@전남중부권@나주@남평@d4@180123-12:34@0.45@0@TBI@0.5@-9999.0@탁도@NTU<br>NJ-NP-TEI-01@나주 남평(배) 온도@NJ-NP@금영섬@전남중부권@나주@남평@d2@180123-12:34@10.1@0@TEI@30.0@4.0@온도@℃<br>WD-ND-PHI-01@완도 노두(배) pH@WD-ND@금영성@전남서남권@완도@대야@null@null@0.0@0@PHI@7.5@6.5@PH@PH<br>WD-ND-TEI-01@완도 노두(배) 온도@WD-ND@금영성@전남서남권@완도@대야@null@null@0.0@0@TEI@30.0@4.0@온도@℃";
        System.out.print(temp);
        String[] temp2 = temp.split("<br>");
        data = new String[temp2.length][16];
        for (int i = 0; i< temp2.length; i++ ) {
            data[i] = new String[15];
            data[i] = temp2[i].split("@");
        }

        location1IDs = new HashSet<>();
        for(int i=0; i<data.length; i++)
            location1IDs.add(i);
    }

    public DataHandler(String[] dataIDs) {
        location4IDs = new HashSet<>();
        for (String number: dataIDs)
            location4IDs.add(Integer.parseInt(number));
        //TODO dddddddddddddddddddddddddd
        System.out.print(location4IDs);
    }

    @Override
    protected String doInBackground(Void... params) {

        String result; // 요청 결과를 저장할 변수.
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
        // TODO 이거 주석처리하면 원래랑 똑같이 실행됨
//        String[] lines = s.split("<br>");

        System.out.print(s);

//        data = new String[lines.length][15];
//        for(int i = 0; i < lines.length; i++){
//            data[i] = lines[i].split("@");
//        }



    }

    public ArrayList<String> getNextLocationCandidate() {
        HashSet<String> hashSet = new HashSet<>();

        for (int i:location1IDs) {
            hashSet.add(data[i][3]);
        }
        ArrayList<String> location1s = new ArrayList<>();
        location1s.add(0, "선택");
        location1s.addAll(hashSet);

        return location1s;
    }

    public ArrayList<String> getNextLocationCandidate(String location1) {
        HashSet<String> hashSet = new HashSet<>();
        location2IDs = new HashSet<>();
        for(int i: location1IDs) {
            if(data[i][3].equals(location1)) {
                hashSet.add(data[i][4]);
                location2IDs.add(i);
            }
        }
        ArrayList<String> location2s = new ArrayList<>();
        location2s.add(0,"선택");
        location2s.addAll(hashSet);

        return location2s;
    }

    public ArrayList<String> getNextLocationCandidate(String location1, String location2) {
        HashSet<String> hashSet = new HashSet<>();
        location3IDs = new HashSet<>();
        for(int i: location2IDs) {
            if(data[i][3].equals(location1) && data[i][4].equals(location2)) {
                hashSet.add(data[i][5]);
                location3IDs.add(i);
            }
        }
        ArrayList<String> location3s = new ArrayList<>();
        location3s.add(0,"선택");
        location3s.addAll(hashSet);

        return location3s;
    }

    public ArrayList<String> getNextLocationCandidate(String location1, String location2, String location3) {
        HashSet<String> hashSet = new HashSet<>();
        location4IDs = new HashSet<>();
        for(int i: location3IDs) {
            if(data[i][3].equals(location1) && data[i][4].equals(location2) && data[i][5].equals(location3)) {
                hashSet.add(data[i][6]);
                location4IDs.add(i);
            }
        }
        ArrayList<String> location4s = new ArrayList<>();
        location4s.add(0,"선택");
        location4s.addAll(hashSet);

        return location4s;
    }

    public String[] getCriteriaData(int i) {
        String[] result = new String[4];
        result[0] = data[i][14];
        if(Double.parseDouble(data[i][13]) == -9999.0)
            result[1] = "< " + data[i][12];
        else
            result[1] = data[i][13] + " ~ " + data[i][12];
        result[2] = data[i][9] + " " + data[i][15];
        if(data[i][10].equals("1")){
            result[3] = "O";
        } else
            result[3] = "X";

        return result;
    }

    public HashSet<Integer> getLocation4IDs() {
        return location4IDs;
    }



    public String[][] getData() {
        return data;
    }
}
