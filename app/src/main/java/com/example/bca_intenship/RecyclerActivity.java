package com.example.bca_intenship;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
    RecyclerView recycler;

    String[] nameArray = {"Appple","Orange","Apricot","Watermelon"};
    String[] priceArray = {"₹120","₹30","₹100","₹50","₹40"};
    int[] imageArray = {R.drawable.apple,R.drawable.orange,R.drawable.apricot,R.drawable.watermelon};

    ArrayList<FruitList> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler);

        recycler = findViewById(R.id.Recycler);
        recycler.setLayoutManager(new LinearLayoutManager(RecyclerActivity.this));

        arrayList = new ArrayList<FruitList>();
        for (int  i=0;i<nameArray.length;i++){
            FruitList list = new FruitList();
            list.setName(nameArray[i]);
            list.setPrice(priceArray[i]);
            list.setImage(imageArray[i]);
            arrayList.add(list);

        }
        FruitAdapter adapter = new FruitAdapter(RecyclerActivity.this,arrayList);
        recycler.setAdapter(adapter);

    }
}