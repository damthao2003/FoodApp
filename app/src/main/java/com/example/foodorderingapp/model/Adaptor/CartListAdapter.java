package com.example.foodorderingapp.model.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorderingapp.model.Domain.FoodDomain;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.model.helper.ManagementCart;
import com.example.foodorderingapp.model.interfaces.ChangeNumberItemListener;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<FoodDomain> foodDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemListener changeNumberItemListener;
    public void setFoodDomains(ArrayList<FoodDomain> foodDomains) {
        this.foodDomains = foodDomains;
        notifyDataSetChanged(); // Thông báo cho adapter rằng dữ liệu đã thay đổi
    }

    public CartListAdapter(ArrayList<FoodDomain> foodDomains, Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.foodDomains = foodDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(foodDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round(foodDomains.get(position).getNumberInCart() * foodDomains.get(position).getFee() * 100)/100));
        holder.num.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));

        // Lấy URL của hình ảnh từ đối tượng FoodDomain
        String imageUrl = foodDomains.get(position).getPic();

        // Sử dụng Glide để tải hình ảnh từ URL vào ImageView trong ViewHolder
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.pic);

        // Gắn các sự kiện cho các nút plus và minus ở đây nếu cần thiết

        holder.plusItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberFood(foodDomains,position, new ChangeNumberItemListener(){
                   public void changed(){
                       notifyDataSetChanged();
                       changeNumberItemListener.changed();
                   }
                });
            }
        });
        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusNumberFood(foodDomains,position,new ChangeNumberItemListener(){
                   public void changed(){
                       notifyDataSetChanged();
                       changeNumberItemListener.changed();
                   }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, feeEachItem;
        ImageView pic, plusItems, minusItem;
        TextView totalEachItem, num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.foodName);
            feeEachItem = itemView.findViewById(R.id.feeEachitem);
            pic = itemView.findViewById(R.id.picCart);
            totalEachItem = itemView.findViewById(R.id.totalEachitem);
            num = itemView.findViewById(R.id.numberItemTxt);
            plusItems = itemView.findViewById(R.id.plusCartitem);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
        }
    }
}
