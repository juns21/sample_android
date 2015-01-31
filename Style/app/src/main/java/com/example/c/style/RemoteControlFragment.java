package com.example.c.style;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by c on 2015-01-31.
 */
public class RemoteControlFragment extends Fragment {
    private TextView mSelectedTextView;
    private TextView mWorkingTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_remote_control, container, false);
        mSelectedTextView = (TextView) v.findViewById(R.id.fragment_remote_control_selectedTextView);
        mWorkingTextView = (TextView) v.findViewById(R.id.fragment_remote_control_workingTextView);

        Button btnZero = (Button) v.findViewById(R.id.fragment_remote_control_zeroButton);
        Button btnOne = (Button) v.findViewById(R.id.fragment_remote_control_oneButton);
        Button btnEnter = (Button) v.findViewById(R.id.fragment_remote_control_enterButton);

        View.OnClickListener numberButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v;
                String text = textView.getText().toString();
                String working = mWorkingTextView.getText().toString();
                mWorkingTextView.setText(working + text);
            }
        };
        btnZero.setOnClickListener(numberButtonListener);
        btnOne.setOnClickListener(numberButtonListener);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String working = mWorkingTextView.getText().toString();
                mSelectedTextView.setText(working);
            }
        });

        return v;
    }
}
