package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class B extends AppCompatActivity {
    EditText PM10,PM25;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        PM10=findViewById(R.id.PM10);
        PM25=findViewById(R.id.PM25);
        Intent intent=getIntent();
        Bundle bundleData=intent.getBundleExtra("SAMPLE_DATA");
        if(bundleData==null)
        {
            Toast.makeText(this,"NULL",Toast.LENGTH_LONG).show();
            return;
        }
        PM10.setText(bundleData.getString("PM10"));
        PM25.setText(bundleData.getString("PM25"));
    }
}
