package com.example.c.sqlite;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewResult = (TextView) findViewById(R.id.textViewResult);

        MySQLiteHandler myHandler = new MySQLiteHandler(this);
        myHandler.insert("kim", 20, "seoul");
        myHandler.insert("lee", 21, "incheon");
        myHandler.insert("jung", 22, "busan");
        myHandler.insert("cho", 23, "daegu");
        myHandler.insert("hwang", 24, "daegeon");
        myHandler.insert("ji", 25, "sokcho");
        myHandler.insert("choi", 26, "sanbon");

        myHandler.updateAge("lee", 50);
        myHandler.delete("ji");

        textViewResult.setText(myHandler.getAllData());
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
