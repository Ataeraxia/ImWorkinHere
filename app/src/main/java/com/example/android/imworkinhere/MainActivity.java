package com.example.android.imworkinhere;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int token = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        token = sharedPref.getInt("token", 42);
        setToken();

        Button workButt = (Button) findViewById(R.id.work);
        workButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                token++;
                setToken();
            }
        });

        Button rewardButt = (Button) findViewById(R.id.reward);
        rewardButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                token--;
                setToken();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putInt("token", token);
        saveToken();
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        token = savedInstanceState.getInt("token");
    }

    private void setToken(){
        TextView tokenText = (TextView) findViewById(R.id.token);
        tokenText.setText(String.valueOf(token));
    }

    private void saveToken() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("token", token);
        prefEditor.commit();
    }
}
