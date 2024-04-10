package com.example.foodorderingapp.model.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.model.Domain.FoodDomain;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.viewholder> {
    ArrayList<FoodDomain> items;
//    Context context;

    public FoodListAdapter(ArrayList<FoodDomain> items) {
        this.items = items;
    }

    @Override
//    public FoodListAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        context = parent.getContext();
////        View inflate = LayoutInflater.from(context).inflate(R.layout.viewholder_list_food,parent, false);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list_food,parent, false);
//        return new viewholder(view);
//    }
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list_food,parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListAdapter.viewholder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.feeTxt.setText(String.valueOf(items.get(position).getFee()));
        holder.startTxt.setText(items.get(position).getStart());

//        Glide.with(context)
//                .load(items.get(position).getPic())
//                .transform(new CenterCrop(), new RoundedCorners(30))
//                .into(holder.pic);
        Glide.with(holder.itemView.getContext())
                .load(items.get(position).getPic())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView titleTxt, feeTxt, startTxt;
        ImageView pic;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            feeTxt = itemView.findViewById(R.id.feeTxt);
            startTxt = itemView.findViewById(R.id.startTxt);
            pic = itemView.findViewById(R.id.img);
        }
    }
}
