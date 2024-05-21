package com.example.bca_intenship;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.security.Signature;

public class MainActivity extends AppCompatActivity {


    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.main_button);

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               System.out.println("Login Successsfully");

               Toast.makeText(MainActivity.this,"Login successfully",Toast.LENGTH_LONG).show();

           Intent intent = new Intent(MainActivity.this, SingupActivity.class);
           startActivity(intent);


           }
       });


    }
}