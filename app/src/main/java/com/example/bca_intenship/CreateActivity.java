package com.example.bca_intenship;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class CreateActivity extends AppCompatActivity {

    EditText username,email,contact,password,confirmpassword;
    Button singup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    RadioGroup gender;

    Spinner city ;

    CheckBox term;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);

        username = findViewById(R.id.create_username);
        email = findViewById(R.id.create_email);
        contact = findViewById(R.id.create_contact);
        password = findViewById(R.id.create_password);
        confirmpassword = findViewById(R.id.create_confirmpassword);
        singup = findViewById(R.id.create_button);
        term = findViewById(R.id.create_term);

        


        city = findViewById(R.id.create_city);
        
        
        String cityArray[] = {"Ahemdabad","Rajkot","Surat","Junagadh","Vadodara"};
        
         ArrayAdapter adapter = new ArrayAdapter(CreateActivity.this, android.R.layout.select_dialog_item,cityArray);
          city.setAdapter(adapter);

        gender = findViewById(R.id.create_gender);

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rd = findViewById(i);
                new CommonMethod(CreateActivity.this,rd.getText().toString());
            }
        });






        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                password.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (password.getText().toString().trim().equals("")) {
                            password.setError("Pass required");


                        }
                        else if (password.getText().toString().length()<6) {
                            password.setError("Min 6 char required ");
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            confirmpassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (confirmpassword.getText().toString().equals("")) {
                        confirmpassword.setError("Confirm password");
                    }


                    else if (!password.getText().toString().trim().matches(confirmpassword.getText().toString())) {
                        confirmpassword.setError("Pass does not match");

                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });



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

               else if (gender.getCheckedRadioButtonId() == -1) {
                    new CommonMethod(CreateActivity.this,"Please select gender");
                }
                else if(!term.isChecked()){
                    new CommonMethod(CreateActivity.this,"Please select the terms & condition");
                }

            }
        });




    }
}