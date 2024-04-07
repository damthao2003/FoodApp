package com.example.foodorderingapp.model.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.model.Domain.FoodDomain;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ArrayList;

public class FoodAdaptor extends RecyclerView.Adapter<FoodAdaptor.ViewHolder> {

   ArrayList<FoodDomain> popularFood;

    public FoodAdaptor(ArrayList<FoodDomain> popularFood) {
        this.popularFood = popularFood;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_food, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdaptor.ViewHolder holder, int position) {
        holder.title.setText(popularFood.get(position).getTitle());

        // Tạo một đối tượng NumberFormat cho tiền tệ Việt Nam
        //NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        // Định dạng số tiền theo tiền tệ Việt Nam
//        String formattedFee = formatter.format(popularFood.get(position).getFee());
        // Hiển thị số tiền đã định dạng ra màn hình
//        holder.fee.setText(formattedFee);

        holder.fee.setText(String.valueOf( popularFood.get(position).getFee()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularFood.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).
                load(drawableResourceId).
                into(holder.pic);

    }

    @Override
    public int getItemCount() {

        return popularFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, fee, addBtn;
        ImageView pic;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            fee = itemView.findViewById(R.id.fee);
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);

        }
    }
}
