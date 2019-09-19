package com.example.ehtesham.fleetdoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ehtesham.fleetdoc.activity.FindBikeActivity;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.edtName);
        password = (EditText) findViewById(R.id.edtPass);
        signin = (Button) findViewById(R.id.btnSignIn);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!(username.getText().toString().equals(""))
                        && !(password.getText().toString().equals(""))) {
                    Intent intent = new Intent(LoginActivity.this,FindBikeActivity.class);
                    startActivity(intent);

                }
                else
                {
                    if (username.getText().toString().equals("")) {
                        username.setError("Phone Cant be null");
                    }


                    if (password.getText().toString().equals(""))
                        password.setError("Password cannot be Empty");
                }
            }
        });

    }
}


