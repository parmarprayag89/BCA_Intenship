package com.example.bca_intenship;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SingupActivity extends AppCompatActivity {

    TextView name,password;

    SharedPreferences sp;
    Button logout,profile,delete;

    SQLiteDatabase sqldb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_singup);
        sp = getSharedPreferences(ConstantSp.PREF,MODE_PRIVATE);

        sqldb = openOrCreateDatabase("BCA_Intenship.db",MODE_PRIVATE,null);

        String tableQuery = "CREATE TABLE IF NOT EXISTS USER(USERID INTEGER PRIMARY KEY, NAME VARCHAR(50),CONTACT BIGINT(10),EMAIL VARCHAR(50),PASSWORD VARCHAR(20),GENDER VARCHAR(6)) ";
        sqldb.execSQL(tableQuery);


        name = findViewById(R.id.singup_text);
        password = findViewById(R.id.singup_password);

        name.setText(sp.getString(ConstantSp.EMAIL,""));
        password.setText(sp.getString(ConstantSp.PASSWORD,""));

        Log.d("DATA",sp.getString(ConstantSp.GENDER,"")+"\n"+
                        sp.getString(ConstantSp.PASSWORD,"")+"\n"+
                              sp.getString(ConstantSp.ID,"")+"\n"+
                        sp.getString(ConstantSp.CONTACT,"")+"\n"+
                        sp.getString(ConstantSp.NAME,"")+"\n"+
                sp.getString(ConstantSp.EMAIL,""));

        logout = findViewById(R.id.singup_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                new CommonMethod(SingupActivity.this,MainActivity.class);
                finish();

            }
        });

        profile =findViewById(R.id.singup_profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CommonMethod(SingupActivity.this,ProfileActivity.class);
            }
        });

        delete = findViewById(R.id.singup_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deleteQuery = "DELETE FROM USER WHERE USERID='"+sp.getString(ConstantSp.ID,"")+"' ";
                sqldb.execSQL(deleteQuery);
                new CommonMethod(SingupActivity.this,"Delete successfully");

                sp.edit().clear().commit();
                new CommonMethod(SingupActivity.this,MainActivity.class);
                finish();

            }
        });





////
//        Bundle bundle = getIntent().getExtras();
//
//        String semail = bundle.getString("name");
//        String spassword = bundle.getString("pass");
////
    }
}