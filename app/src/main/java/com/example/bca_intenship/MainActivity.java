package com.example.bca_intenship;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
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
import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {


    Button login;
    TextView createAnaccount;

    ImageView passwordview,passwordhide;
    EditText email,password;

    SQLiteDatabase sqldb;
    SharedPreferences sp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        sp =getSharedPreferences(ConstantSp.PREF,MODE_PRIVATE);



        sqldb = openOrCreateDatabase("BCA_Intenship.db",MODE_PRIVATE,null);

        String tableQuery = "CREATE TABLE IF NOT EXISTS USER(USERID INTEGER PRIMARY KEY  AUTOINCREMENT, NAME VARCHAR(50),CONTACT BIGINT(10),EMAIL VARCHAR(50),PASSWORD VARCHAR(20),GENDER VARCHAR(6)) ";
        sqldb.execSQL(tableQuery);

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


                      String selectQuery = "SELECT * FROM USER WHERE (EMAIL='"+email.getText().toString()+"' OR NAME='"+email.getText().toString()+"') AND PASSWORD ='"+password.getText().toString()+"'";
                        Cursor cursor = sqldb.rawQuery(selectQuery,null);

                        if(cursor.getCount()>0) {

                            while (cursor.moveToNext()){
                                String sUserId = cursor.getString(0);
                                String sName = cursor.getString(1);
                                String sContact = cursor.getString(2);
                                String sEmail = cursor.getString(3);
                                String sPassword = cursor.getString(4);
                                String sGender = cursor.getString(5);

                                Log.d("RESPONSE" ,sUserId+"___"+sGender);


                                sp.edit().putString(ConstantSp.ID,sUserId).commit();
                                sp.edit().putString(ConstantSp.NAME,sName).commit();
                                sp.edit().putString(ConstantSp.CONTACT,sContact).commit();
                                sp.edit().putString(ConstantSp.EMAIL,sEmail).commit();
                                sp.edit().putString(ConstantSp.PASSWORD,sPassword).commit();
                                sp.edit().putString(ConstantSp.GENDER,sGender).commit();






//                                Intent intent = new Intent(MainActivity.this, SingupActivity.class);
//
//                                Bundle bundle = new Bundle();
//
//                                bundle.putString("name", sEmail);
//                                bundle.putString("pass", password.getText().toString());
//                                intent.putExtras(bundle);
//
//                                startActivity(intent);
                            }
                            new CommonMethod(MainActivity.this,SingupActivity.class);

                        }
                        else {
                            new CommonMethod(MainActivity.this,"Login Unsuccessfully");
                        }
                    }




                }
            });
        }


    }

