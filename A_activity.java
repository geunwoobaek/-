package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class A_activity extends AppCompatActivity {
    TextView MSTSRE_NM;
    EditText NO2,O3,CO,SO2,PM10,PM25;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_activity);
        Intent intent=getIntent();
        Bundle bundleData=intent.getBundleExtra("SAMPLE_DATA");
        if(bundleData==null)
        {
            Toast.makeText(this,"NULL",Toast.LENGTH_LONG).show();
            return;
        }
        MSTSRE_NM=findViewById(R.id.MSTSTE_NM);
        NO2=findViewById(R.id.NO2);
        O3=findViewById(R.id.O3);
        CO=findViewById(R.id.CO);
        SO2=findViewById(R.id.SO2);
        PM10=findViewById(R.id.PM10);
        PM25=findViewById(R.id.PM25);
        ////////////////////////////////////
        MSTSRE_NM.setText(bundleData.getString("MSTSRE_NM"));
        NO2.setText(bundleData.getString("NO2"));
        O3.setText(bundleData.getString("O3"));
        CO.setText(bundleData.getString("CO"));
        SO2.setText(bundleData.getString("SO2"));
        PM10.setText(bundleData.getString("PM10"));
        PM25.setText(bundleData.getString("PM25"));
    }
}
