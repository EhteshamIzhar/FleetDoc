package com.example.ehtesham.fleetdoc.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ehtesham.fleetdoc.R;

public class LastActivity extends AppCompatActivity {

    TextView summaryText;
    Button close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        Integer brakes,bumps,topple;
        close = (Button) findViewById(R.id.surp_button);

        brakes = getIntent().getIntExtra("brakes",0);
        bumps = getIntent().getIntExtra("bumps",0);
        topple = getIntent().getIntExtra("topple",0);

        summaryText = (TextView) findViewById(R.id.giftImage);

        summaryText.setText("You have  applied "+brakes+" brakes and have undergone "+bumps+" bumps and your vehicle had "+topple+" topple.");


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
