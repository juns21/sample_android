package com.example.c.br;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    MyBR br;
    MyBR_noti brNoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.c.br");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        brNoti = new MyBR_noti();
        String action = "com.example.c.br";
        IntentFilter filter = new IntentFilter(action);
        registerReceiver(brNoti, filter);

        /*br = new MyBR();
        String action = "android.provider.Telephony.SMS_RECEIVED";
        //registerReceiver(br, android.provider.Telephony.SMS_RECEIVED);

        IntentFilter filter = new IntentFilter(action);
        registerReceiver(br, filter);*/

    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(brNoti);

        /*unregisterReceiver(br);*/
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
