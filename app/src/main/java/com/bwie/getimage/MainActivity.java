package com.bwie.getimage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String urlPath = "http://api.kkmh.com/v1/daily/comic_lists/0";
    private List<Bean.DataBean.ComicsBean> list;
    private ListView listView;
    private MyAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Gson gson = new Gson();
            Bean bean = gson.fromJson(msg.obj.toString(), Bean.class);
            list.addAll(bean.getData().getComics());
            adapter.notifyDataSetChanged();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();
    }

    private void loadData() {
        new Thread() {
            @Override
            public void run() {
                String str = UrlUtils.getUrlConnect(urlPath);
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = str;
                handler.sendMessage(msg);
            }
        }.start();
    }

    private void initView() {
        list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listview);
        adapter = new MyAdapter(MainActivity.this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("image", list.get(position).getCover_image_url());
                intent.setClass(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
