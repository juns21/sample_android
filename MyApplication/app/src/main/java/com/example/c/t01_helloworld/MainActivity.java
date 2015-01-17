package com.example.c.t01_helloworld;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void OnMyClick(View v) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fr = fm.findFragmentById(R.id.frame);

        switch (v.getId()) {
            case R.id.btnAdd:
                if(fr == null) {
                    FragmentTransaction tr = fm.beginTransaction();
                    MyFragment myFr = new MyFragment();
                    tr.add(R.id.frame, myFr, "myFr");
                    tr.addToBackStack(null);
                    tr.commit();
                }
                break;
            case R.id.btnRemove:
                if (fr != null) {
                    FragmentTransaction tr = fm.beginTransaction();
                    tr.remove(fr);
                    tr.commit();
                }
                break;
            case R.id.btnHide:
                if (fr != null) {
                    FragmentTransaction tr = fm.beginTransaction();
                    if (fr.isHidden()) {
                        tr.show(fr);
                    }else {
                        tr.hide(fr);
                    }
                    tr.commit();
                }
                break;
            case R.id.btnReplace:
                if (fr != null) {
                    FragmentTransaction tr = fm.beginTransaction();
                    if (fr.getTag() == "myFr") {
                        TextFragment textFragment = new TextFragment();
                        tr.replace(R.id.frame, textFragment, "textFr");
                    }else {
                       MyFragment myFr = new MyFragment();
                        tr.replace(R.id.frame, myFr, "myFr");
                    }
                    tr.addToBackStack(null);
                    tr.commit();
                }
                break;
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
