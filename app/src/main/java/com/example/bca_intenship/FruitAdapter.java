package com.example.bca_intenship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.MyHolder> {

    Context context;
    ArrayList<FruitList> arrayList;
    public FruitAdapter(Context context, ArrayList<FruitList> arrayList) {
        this.context=context;
        this.arrayList=arrayList;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_fruit_category,parent,false);
        return new MyHolder(view);
    }
    public class MyHolder extends  RecyclerView.ViewHolder {

        TextView name,price;
        ImageView imageView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.custom_fruit_name);
            price=itemView.findViewById(R.id.custom_fruit_price);
            imageView=itemView.findViewById(R.id.custom_fruit_category);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.price.setText(arrayList.get(position).getPrice());
        holder.imageView.setImageResource(arrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
