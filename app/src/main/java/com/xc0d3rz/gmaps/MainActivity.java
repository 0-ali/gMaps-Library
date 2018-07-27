package com.xc0d3rz.gmaps;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.xc0d3rz.gmaps.gstatic.gStatic;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.activity_main);
        ImageView staticMap = (ImageView) findViewById(R.id.static_map);
        List<String> Mylist = new ArrayList<String>();
        Mylist.add("44.8765065,-0.4444849");
        Mylist.add("44.8843778,-0.1368667");
        staticMap.setImageBitmap(gStatic.getMapAsBitmap("45.291002,-0.868131", "44.683159,-0.405704", Mylist));

    }


}
