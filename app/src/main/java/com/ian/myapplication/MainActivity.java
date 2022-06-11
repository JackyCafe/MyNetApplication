package com.ian.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv ;
    ArrayList<SiteName> siteNames;
    NetAdapter adapter ;
    Message msg;
    Bundle bundle;
    private LinearLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        // 設置格線
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        GetWebInfo dTask = new GetWebInfo();
        siteNames = new ArrayList<>();
        dTask.execute("http://eclass.hust.edu.tw/filedownload/166024/fdd76dd427c73c2039606c2b6d9b1b3a/台中市景點資料.json");


    }


    class GetWebInfo extends AsyncTask<String,Void,String>{


        @Override
        protected String doInBackground(String... strings) {

            String result =  getData(strings[0]);
            return result;
        }

        @Override
        protected void onPostExecute(String msg) {
            super.onPostExecute(msg);
            try {
                JSONArray array = new JSONArray(msg);
                for (int i=0; i<array.length(); i++) {
                    JSONObject site = array.getJSONObject(i);
                    //2-2
                    String name = site.getString("名稱");
                    String address = site.getString("鄉鎮市區")+site.getString("地址");

                    String lon = site.getString("東經");
                    String lat = site.getString("北緯");
                    SiteName siteName = new SiteName(name,address,lon,lat);
                    //
                    siteNames.add(siteName);


                }
            } catch (JSONException e) {
                Log.v("tag",e.toString());
                e.printStackTrace();
            }
            if (siteNames.size()!=0){
                adapter = new NetAdapter();
                adapter.setmData(siteNames);
                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
    }







    public String getData(String strUrl){
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream in = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(reader);
            String str;
            while((str = br.readLine())!=null){
                sb.append(str);
            }

            JSONArray array = new JSONArray(sb.toString());

        } catch (Exception e) {
            Log.v("tag",e.toString());
            e.printStackTrace();
        }finally {
        }
        return sb.toString();
    }

//    class uiHandler extends Handler {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            Bundle bundle = msg.getData();
//            List<SiteName> data =(List<SiteName>)bundle.getSerializable("data");
//            adapter = new NetAdapter(data);
//            rv.setAdapter(adapter);
//            adapter.notifyDataSetChanged();
//        }
//
//
//    }
}