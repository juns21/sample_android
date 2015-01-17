package com.example.c.t01_helloworld;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_my, container, false);
        View v = inflater.inflate(R.layout.fragment_my, container, false);

        Button myButton =  (Button)v.findViewById(R.id.myButton);
        final TextView myTextView = (TextView) v.findViewById(R.id.textView);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count;
                count = Integer.parseInt(myTextView.getText().toString());
                myTextView.setText(""+(count++));
            }
        });

        return v;
    }
}
