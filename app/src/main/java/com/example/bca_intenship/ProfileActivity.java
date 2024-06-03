package com.example.bca_intenship;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    EditText username,email,contact,password,confirmpassword;
    Button edit,submit;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    RadioGroup gender;
    RadioButton male,female;
    String sGender;
    String sPassword;

    SharedPreferences sp;
    


    SQLiteDatabase sqldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        sp = getSharedPreferences(ConstantSp.PREF,MODE_PRIVATE);


        sqldb = openOrCreateDatabase("BCA_Intenship.db",MODE_PRIVATE,null);

        String tableQuery = "CREATE TABLE IF NOT EXISTS USER(USERID INTEGER PRIMARY KEY, NAME VARCHAR(50),CONTACT BIGINT(10),EMAIL VARCHAR(50),PASSWORD VARCHAR(20),GENDER VARCHAR(6)) ";
        sqldb.execSQL(tableQuery);




        username = findViewById(R.id.profile_username);
        email = findViewById(R.id.profile_email);
        contact = findViewById(R.id.profile_contact);
        password = findViewById(R.id.profile_password);
        confirmpassword = findViewById(R.id.profile_confirmpassword);
        submit = findViewById(R.id.profile_button_submit);
        edit = findViewById(R.id.profile_button_edit);

        male = findViewById(R.id.profile_male);
        female = findViewById(R.id.profile_female);
        

        gender = findViewById(R.id.profile_gender);

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = findViewById(i);
                sGender = rb.getText().toString();
                new CommonMethod(ProfileActivity.this,sGender);
            }
        });


        Log.d("UPDATE",sp.getString(ConstantSp.GENDER,"")+"\n"+
                sp.getString(ConstantSp.PASSWORD,"")+"\n"+
                sp.getString(ConstantSp.ID,"")+"\n"+
                sp.getString(ConstantSp.CONTACT,"")+"\n"+
                sp.getString(ConstantSp.NAME,"")+"\n"+
                sp.getString(ConstantSp.EMAIL,""));




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().trim().equals("")){
                    username.setError("username required");

                }
                else if(email.getText().toString().trim().equals("")){
                    email.setError("Email id required");

                } else if (!email.getText().toString().matches(emailPattern)) {
                    email.setError("Valid emailid required");
                }
                else if (contact.getText().toString().trim().equals("")) {
                    contact.setError("Contact no. required");
                }
                else   if (password.getText().toString().trim().equals("")) {
                    password.setError("Pass required");
                }
                else if (password.getText().toString().length()<6) {
                    password.setError("Min 6 char required ");
                }
                else   if (confirmpassword.getText().toString().equals("")) {
                    confirmpassword.setError("Confirm password");
                }
                else if (!password.getText().toString().trim().matches(confirmpassword.getText().toString())) {
                    confirmpassword.setError("Pass does not match");
                }
                else if (gender.getCheckedRadioButtonId() == -1) {
                    new CommonMethod(ProfileActivity.this,"Please select gender");
                }
                else{
                    String updateQuery = "UPDATE USER SET NAME='"+username.getText().toString()+"',CONTACT='"+contact.getText().toString()+"',EMAIL='"+email.getText().toString()+"',PASSWORD='"+password.getText().toString()+"',GENDER='"+sGender+"' WHERE USERID='"+sp.getString(ConstantSp.ID,"")+"' ";
                    sqldb.execSQL(updateQuery);
                    new CommonMethod(ProfileActivity.this,"Update Sucessfully");

                }

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setData(true);
            }
        });

        setData(false);

    }

    private void setData(boolean b) {
        username.setText(sp.getString(ConstantSp.NAME,""));
        contact.setText(sp.getString(ConstantSp.CONTACT,""));
        email.setText(sp.getString(ConstantSp.EMAIL,""));
        password.setText(sp.getString(ConstantSp.PASSWORD,""));
        confirmpassword.setText(sp.getString(ConstantSp.PASSWORD,""));

        sGender = sp.getString(ConstantSp.GENDER,"");
        if (sGender.equals("Male")){
            male.setChecked(true);
            female.setChecked(false);
        }
        else if (sGender.equals("Female")){
            male.setChecked(false);
            female.setChecked(true);
        }
        else {
            male.setChecked(false);
            female.setChecked(false);
        }
        username.setEnabled(b);
        contact.setEnabled(b);
        email.setEnabled(b);
        password.setEnabled(b);
        confirmpassword.setEnabled(b);

        male.setEnabled(b);
        female.setEnabled(b);

        if(b){
            edit.setVisibility(View.GONE);
            confirmpassword.setVisibility(View.VISIBLE);
            submit.setVisibility(View.VISIBLE);

        }
        else {
            edit.setVisibility(View.VISIBLE);
            confirmpassword.setVisibility(View.GONE);
            submit.setVisibility(View.GONE);

        }
    }

}