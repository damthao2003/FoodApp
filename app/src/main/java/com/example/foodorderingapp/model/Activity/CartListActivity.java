package com.example.foodorderingapp.model.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.model.interfaces.ChangeNumberItemListener;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.model.helper.ManagementCart;
import com.example.foodorderingapp.model.Adaptor.CartListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ImageView imageView;
    private ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);
        initView();
        initList();
        CalculateCart();
        bottomNavigation();
        Button thanhToanButton = findViewById(R.id.button2);
        thanhToanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPaymentSuccessDialog();
            }
        });
    }

    private void bottomNavigation(){
//        LinearLayout cardBtn = findViewById(R.id.cartBtn);
//        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        ImageView imageView5 = findViewById(R.id.imageView5);
        ImageView imageView3 = findViewById(R.id.imageView3);

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(CartListActivity.this, CartListActivity.class));
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, MainActivity.class));
            }
        });
}

    private void initView() {
        recyclerViewList = findViewById(R.id.cartView);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollview);
        recyclerViewList = findViewById(R.id.cartView);
    }
    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCart(){
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round(managementCart.getTotalFee() * percentTax) * 100 / 100;
        double total = Math.round(managementCart.getTotalFee() + tax + delivery) * 100 / 100;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100/ 100);

        totalFeeTxt.setText("VND " + itemTotal);
        taxTxt.setText("VND " + tax);
        deliveryTxt.setText("VND " + delivery);
        totalTxt.setText("VND " + total);
    }
    private void showPaymentSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Html.fromHtml("<font color='#FF0000'>Thanh toán thành công</font>"))
                .setMessage(Html.fromHtml("<font color='#000000'>Cảm ơn bạn đã thanh toán.</font>"))
                .setPositiveButton(Html.fromHtml("<font color='#FF0000'>OK</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xóa thông tin giỏ hàng
                        managementCart.clearCart();
                        // Cập nhật giao diện người dùng
                        updateUI();
                        // Chuyển hướng về MainActivity
                        Intent intent = new Intent(CartListActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

        // Hiển thị AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updateUI() {
        // Cập nhật giao diện người dùng sau khi xóa thông tin giỏ hàng
        totalFeeTxt.setText("VND 0");
        taxTxt.setText("VND 0");
        deliveryTxt.setText("VND 0");
        totalTxt.setText("VND 0");
        adapter.notifyDataSetChanged();
        emptyTxt.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
    }

}