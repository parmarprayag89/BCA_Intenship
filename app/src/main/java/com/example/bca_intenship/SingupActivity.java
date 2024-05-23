package com.example.bca_intenship;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SingupActivity extends AppCompatActivity {

    TextView name,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_singup);

        name = findViewById(R.id.singup_text);
        password = findViewById(R.id.singup_password);


        Bundle bundle = getIntent().getExtras();

        String semail = bundle.getString("name");
        String spassword = bundle.getString("pass");

        name.setText(semail);
        password.setText(spassword);








    }
}