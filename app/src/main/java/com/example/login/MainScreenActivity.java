package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreenActivity extends AppCompatActivity {

    private Button accountButton, manageFoodButton, manageOrdersButton, logoutButton;
    private Switch notificationSwitch;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);  // Thay thế bằng layout của bạn

        // Ánh xạ các phần tử
        accountButton = findViewById(R.id.account_button);
        manageFoodButton = findViewById(R.id.manage_food_button);
        manageOrdersButton = findViewById(R.id.manage_orders_button);
        logoutButton = findViewById(R.id.logout_button);
        notificationSwitch = findViewById(R.id.notification_switch);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Sự kiện khi nhấn nút Đăng xuất
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý đăng xuất (thực tế là chỉ quay lại màn hình đăng nhập)
                Toast.makeText(MainScreenActivity.this, "Đã đăng xuất.", Toast.LENGTH_SHORT).show();
                finish(); // Quay lại màn hình đăng nhập
            }
        });

        // Các sự kiện khác có thể thêm vào đây (ví dụ: Tài khoản, Quản lý món ăn, Quản lý đơn hàng)
    }
}
