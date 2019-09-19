package com.example.ehtesham.fleetdoc.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ehtesham.fleetdoc.R;

public class FindBikeActivity extends AppCompatActivity {

    Button bookRide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_bike);
        bookRide = (Button) findViewById(R.id.Book);
        bookRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindBikeActivity.this,StartRideActivity.class);
                startActivity(intent);
            }
        });
    }
}
