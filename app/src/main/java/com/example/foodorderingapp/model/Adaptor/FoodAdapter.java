package com.example.foodorderingapp.model.Adaptor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.model.Activity.ShowDetailActivity;
import com.example.foodorderingapp.model.Domain.FoodDomain;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

   ArrayList<FoodDomain> popularFood;

    public FoodAdapter(ArrayList<FoodDomain> popularFood) {
        this.popularFood = popularFood;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder,  int position) {
        FoodDomain food = popularFood.get(position);
        if(food == null){
            return;
        }
        holder.foodName.setText(food.getTitle());
        holder.fee.setText(String.valueOf(food.getFee()));

        Glide.with(holder.itemView.getContext()).
                load(food.getPic()).
                into(holder.foodPic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", popularFood.get(position));
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        if(popularFood != null){
            return popularFood.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView foodName, fee, addBtn;
        ImageView foodPic;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            fee = itemView.findViewById(R.id.feeTxt);
            foodPic = itemView.findViewById(R.id.foodPic);
            addBtn = itemView.findViewById(R.id.addBtn);

        }
    }
}
