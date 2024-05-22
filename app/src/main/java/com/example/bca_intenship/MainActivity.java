package com.example.bca_intenship;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.security.Signature;

public class MainActivity extends AppCompatActivity {


    Button login;

    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.main_button);

        email = findViewById(R.id.main_email);
        password = findViewById(R.id.main_pass);


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (email.getText().toString().trim().equals("")) {
                        email.setError("Enter email id ");

                    } else if (password.getText().toString().trim().equals("")) {
                        password.setError("password required ");
                    } else if (password.getText().toString().trim().length()<6) {
                        password.setError("Min 6 char required ");

                    }
                    else {

                        new CommonMethod(MainActivity.this,SingupActivity.class);
                        new CommonMethod(MainActivity.this,"Login Successfuly");
                    }




                }
            });
        }


    }

