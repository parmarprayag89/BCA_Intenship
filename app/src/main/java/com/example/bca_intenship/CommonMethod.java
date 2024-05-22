package com.example.bca_intenship;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class CommonMethod {

    CommonMethod(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();

    }

    CommonMethod(Context context , Class<?> nextclass){
        Intent intent = new Intent(context,nextclass);
        context.startActivity(intent);

    }
    CommonMethod(View view ,String message){
        Snackbar.make(view,message,Snackbar.LENGTH_LONG).show();

    }
}
