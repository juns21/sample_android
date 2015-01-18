package com.example.c.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    static final int MY_THREAD_TEST = 1;
    Thread th;
    TextView textView;
    Handler handler;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.mProgressBar);

        textView = (TextView) findViewById(R.id.textView);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg.what == MY_THREAD_TEST) {
                    textView.setText("count=" + msg.arg1);
                    progressBar.setProgress(msg.arg1);
                }
            }
        };
    }

    int count = 0;
    @Override
    protected void onStart() {
        super.onStart();

        // new Thread().start();
        th = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<100; i++) {
                    count = i;

                    /*Message msg = new Message();
                    msg.what = MY_THREAD_TEST;
                    msg.arg1 = i;
                    handler.sendMessage(msg);*/
                    Message msg = handler.obtainMessage();
                    msg.what = MY_THREAD_TEST;
                    msg.arg1 = i;
                    handler.sendMessage(msg);

                    /*handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("runnable count ="+ count);
                        }
                    });*/
                    /*textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("view post runnable count="+ count);
                        }
                    });*/

                    /*runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("runOnUiThread count = "+count);
                            progressBar.setProgress(count);
                        }
                    });*/

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        th.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
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
