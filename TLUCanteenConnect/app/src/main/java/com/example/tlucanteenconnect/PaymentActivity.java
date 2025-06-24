package com.example.tlucanteenconnect;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class PaymentActivity extends AppCompatActivity {

    // Components from topBar
    private ImageView topBarBackButton;
    private TextView topBarTitle;

    // Components from bottomNavBar
    private ImageView navHome, navList, navBell, navCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Ánh xạ các thành phần từ top bar
        topBarBackButton = findViewById(R.id.btn_back);
        topBarTitle = findViewById(R.id.toolbarTitle);

        // Set tiêu đề cho màn hình "Thanh toán"
        topBarTitle.setText("Thanh toán");
        // Xử lý sự kiện click cho nút back
        topBarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Quay về Activity trước đó (OrderActivity)
            }
        });

        // Ánh xạ các thành phần từ bottom nav bar
        navHome = findViewById(R.id.nav_home);
        navList = findViewById(R.id.nav_list);
        navBell = findViewById(R.id.nav_bell); // ĐÃ SỬA LỖI Ở ĐÂY
        navCart = findViewById(R.id.nav_cart);

        // Highlight icon giỏ hàng (giả định PaymentActivity cũng liên quan đến giỏ hàng)
        navCart.setColorFilter(ContextCompat.getColor(this, R.color.primary_app_color));

        // Xử lý sự kiện click cho các icon ở bottom navigation bar (tùy chọn)
        navHome.setOnClickListener(v -> {
            // Đặt màu cho icon được chọn và reset màu cho các icon khác
            navHome.setColorFilter(ContextCompat.getColor(this, R.color.primary_app_color));
            navList.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navBell.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navCart.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            // Intent to HomeActivity
        });
        navList.setOnClickListener(v -> {
            navHome.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navList.setColorFilter(ContextCompat.getColor(this, R.color.primary_app_color));
            navBell.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navCart.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            // Intent to ListActivity
        });
        navBell.setOnClickListener(v -> {
            navHome.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navList.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navBell.setColorFilter(ContextCompat.getColor(this, R.color.primary_app_color));
            navCart.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            // Intent to NotificationActivity
        });
        navCart.setOnClickListener(v -> {
            navHome.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navList.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navBell.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navCart.setColorFilter(ContextCompat.getColor(this, R.color.primary_app_color));
            // Intent to CartActivity
        });
    }
}