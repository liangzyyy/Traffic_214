package com.mad.trafficclient.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.mad.trafficclient.R;

public class RoadDetailActivity extends Activity {
    ImageView imageView_back;
    Button Roadnext_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_road_detail);
        initView();
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Roadnext_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoadDetailActivity.this,DateSelectActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        imageView_back = (ImageView) findViewById(R.id.roadDetail_back);
        Roadnext_btn = (Button) findViewById(R.id.Roadnext_btn);
    }
}
