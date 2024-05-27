package com.example.bca_intenship;


import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.security.Signature;

public class MainActivity extends AppCompatActivity {


    Button login;
    TextView createAnaccount;

    ImageView passwordview,passwordhide;
    EditText email,password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createAnaccount = findViewById(R.id.main_create_account);

        createAnaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new CommonMethod(MainActivity.this,CreateActivity.class);
            }
        });

        login = findViewById(R.id.main_button);

        email = findViewById(R.id.main_email);
        password = findViewById(R.id.main_pass);
        passwordview = findViewById(R.id.main_pass_view);
        passwordhide = findViewById(R.id.main_pass_hide);

        passwordview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordview.setVisibility(View.GONE);
                passwordhide.setVisibility(View.VISIBLE);
                password.setTransformationMethod(null);


            }
        });
        passwordhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordview.setVisibility(View.VISIBLE);
                passwordhide.setVisibility(View.GONE);
                password.setTransformationMethod(new PasswordTransformationMethod());

            }
        });








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


                       Intent intent = new Intent(MainActivity.this,SingupActivity.class);

                        Bundle bundle = new Bundle();

                        bundle.putString("name",email.getText().toString());
                        bundle.putString("pass",password.getText().toString());
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }




                }
            });
        }


    }

