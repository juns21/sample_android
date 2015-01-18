package com.example.c.domparser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class MainActivity extends ActionBarActivity {
    TextView textViewResult;
    class MyTask extends AsyncTask<String, Void, Document> {
        @Override
        protected Document doInBackground(String... params) {
            URL url;
            Document doc = null;

            try {
                url = new URL(params[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(url.openStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return doc;
        }

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);

            String str = "";
            NodeList nodeList = document.getElementsByTagName("data");
            for (int i=0; i<nodeList.getLength(); i++) {
                str += "\n"+ i +": data ";

                //Node node = nodeList.item(i);           // <data> ... </data>
                //Element dataElement = (Element) node;
                Element dataElement = (Element) nodeList.item(i);

                /*NodeList dayNodeList = dataElement.getElementsByTagName("day");
                Element dayElement = (Element) dayNodeList.item(0);     // <day>....</day>
                NodeList dayTextNodeList = dayElement.getChildNodes();
                //str += dayTextNodeList.item(0).getNodeValue() +"일 ";    // 0
                if (dayTextNodeList.item(0).getNodeValue().equals("0")) {
                    str += "오늘 ";
                }else {
                    str += "내일 ";
                }*/

                str += getNodeValue("day", dataElement)+"일 ";
                str += getNodeValue("hour", dataElement)+"시 ";
                str += getNodeValue("temp", dataElement)+"도 ";
                str += getNodeValue("wfKor", dataElement)+" ";
            }
            textViewResult.setText(str);
        }

        private String getNodeValue(String tagName, Element dataElement) {
            NodeList dayNodeList = dataElement.getElementsByTagName(tagName);
            Element dayElement = (Element) dayNodeList.item(0);     // <day>....</day>
            NodeList dayTextNodeList = dayElement.getChildNodes();
            return dayTextNodeList.item(0).getNodeValue();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = (TextView) findViewById(R.id.textViewResult);

        MyTask task = new MyTask();
        task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153077000");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
