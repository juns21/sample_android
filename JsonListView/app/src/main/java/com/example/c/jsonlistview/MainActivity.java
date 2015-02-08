package com.example.c.jsonlistview;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {
    ListView listView;
    TextView ver;
    TextView name;
    TextView api;
    Button btnGetData;

    ArrayList<HashMap<String, String>> osList = new ArrayList<HashMap<String, String>>();
    String url = "http://api.learn2crack.com/android/jsonos";

    String TAG_OS = "android";
    String TAG_VER = "ver";
    String TAG_NAME = "name";
    String TAG_API = "api";

    JSONArray android = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //osList = new ArrayList<HashMap<String, String>>();
        btnGetData = (Button) findViewById(R.id.getdata);
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONParse().execute();
            }
        });
    }

    class JSONParse extends AsyncTask<String, String, JSONObject>{

        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ver = (TextView)findViewById(R.id.vers);
            name = (TextView)findViewById(R.id.name);
            api = (TextView)findViewById(R.id.api);
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Getting Data...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();

            try {
                android = json.getJSONArray(TAG_OS);//android
                for(int i=0; i<android.length(); i++){
                    JSONObject c = android.getJSONObject(i);
                    String ver = c.getString(TAG_VER);
                    String name = c.getString(TAG_NAME);
                    String api = c.getString(TAG_API);

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(TAG_VER, ver);
                    map.put(TAG_NAME, name);
                    map.put(TAG_API, api);

                    osList.add(map);

                    String[] widgets = new String[]{TAG_VER, TAG_NAME, TAG_API};
                    int[] ids = new int[]{R.id.vers, R.id.name, R.id.api};

                    listView = (ListView)findViewById(R.id.list);
                    ListAdapter adapter = new SimpleAdapter(
                            getApplicationContext(),//MainActivity.this,
                            osList,
                            R.layout.list_v,
                            widgets,
                            ids
                            );
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(MainActivity.this, "You Clicked at " +
                                    osList.get(position).get("name"), Toast.LENGTH_LONG).show();
                        }
                    });
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONParser jParser = new JSONParser();
            JSONObject json = jParser.getJSONFromUrl(url);
            return json;
        }
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
