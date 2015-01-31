package com.example.c.style;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
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

        View.OnClickListener numberButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v;
                String text = textView.getText().toString();
                String working = mWorkingTextView.getText().toString();
                mWorkingTextView.setText(working + text);
            }
        };

        TableLayout tableLayout = (TableLayout) v.findViewById(R.id.fragment_remote_control_tableLayout);
        int number = 1;
        for (int i=2; i<tableLayout.getChildCount()-1; i++) {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
            for (int j=0; j<tableRow.getChildCount(); j++) {
                Button button = (Button) tableRow.getChildAt(j);
                button.setText(""+number);
                number++;
                button.setOnClickListener(numberButtonListener);
            }
        }

        TableRow bottomRow = (TableRow) tableLayout.getChildAt(tableLayout.getChildCount()-1);
        Button deleteButton = (Button) bottomRow.getChildAt(0);
        deleteButton.setText("DELETE");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWorkingTextView.setText("");
            }
        });

        Button zeroButton = (Button) bottomRow.getChildAt(1);
        zeroButton.setText("0");
        zeroButton.setOnClickListener(numberButtonListener);

        Button enterButton = (Button) bottomRow.getChildAt(2);
        enterButton.setText("ENTER");
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String working = mWorkingTextView.getText().toString();
                mSelectedTextView.setText(working);
            }
        });

        return v;
    }
}
