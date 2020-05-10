package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
String html="http://openapi.seoul.go.kr:8088/444f534a6a746274313238625350706b/xml/DailyAverageAirQuality/1/5/";
String html2="http://openapi.seoul.go.kr:8088/444f534a6a746274313238625350706b/xml/DailyAverageAirQuality/1/5/";
    Document doc = null;
    String A;
    EditText date,local,today;
    List<String> mList ;
    Bundle bundledata;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date=findViewById(R.id.date);
        local=findViewById(R.id.localName);
        today=findViewById(R.id.today_local);
        mList= new ArrayList<String>();
        bundledata=new Bundle();
        intent= new Intent();
    }
    public  void Click1(View v)
    {
                GetXMLTask task = new GetXMLTask();
                ComponentName componentName;
                componentName=new ComponentName("com.example.myapplication","com.example.myapplication.A_activity");
                intent.setComponent(componentName);
                html=html2.concat(date.getText().toString());
                html=html.concat("/");
                html=html.concat(local.getText().toString());
                html=html.concat("/");
                Log.e("URL",html);
                task.execute(html);

    }
    public  void Click2(View v)
    {
       ; GetXMLTask task = new GetXMLTask();
        ComponentName componentName;
        componentName=new ComponentName("com.example.myapplication","com.example.myapplication.B");
        intent.setComponent(componentName);
        html=html2.concat("20191001");
        html=html.concat("/");
        html=html.concat(local.getText().toString());
        html=html.concat("/");
        Log.e("URL",html);
        task.execute(html);
    }

    //private inner class extending AsyncTask
    private class GetXMLTask extends AsyncTask<String, Void, Document>{

        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder(); //XML문서 빌더 객체를 생성
                doc = db.parse(new InputSource(url.openStream())); //XML문서를 파싱한다.
                ((org.w3c.dom.Document) doc).getDocumentElement().normalize();

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {


            //data태그가 있는 노드를 찾아서 리스트 형태로 만들어서 반환
            NodeList nodeList = doc.getElementsByTagName("row");
            //data 태그를 가지는 노드를 찾음, 계층적인 노드 구조를 반환

                Node node = nodeList.item(0); //data엘리먼트 노드
                Element element = (Element) node;
                mList.add(element.getElementsByTagName("MSRDT_DE").item(0).getTextContent().toString());
                bundledata.putString("MSRDT_DE",mList.get(0));
                mList.add(element.getElementsByTagName("MSRSTE_NM").item(0).getTextContent().toString());
                bundledata.putString("MSRSTE_NM",mList.get(1));
                mList.add(element.getElementsByTagName("NO2").item(0).getTextContent().toString());
                bundledata.putString("NO2",mList.get(2));
                mList.add(element.getElementsByTagName("O3").item(0).getTextContent().toString());
                bundledata.putString("O3",mList.get(3));
                mList.add(element.getElementsByTagName("CO").item(0).getTextContent().toString());
                bundledata.putString("CO",mList.get(4));
                mList.add(element.getElementsByTagName("SO2").item(0).getTextContent().toString());
                bundledata.putString("SO2",mList.get(5));
                mList.add(element.getElementsByTagName("PM10").item(0).getTextContent().toString());
                bundledata.putString("PM10",mList.get(6));
                Log.e("6",mList.get(6));
                mList.add(element.getElementsByTagName("PM25").item(0).getTextContent().toString());
                bundledata.putString("PM25",mList.get(7));
                intent.putExtra("SAMPLE_DATA",bundledata);
                startActivity(intent);
                Log.e("7",mList.get(7));
                super.onPostExecute(doc);
        }
    }//end inner class - GetXMLTask
}
