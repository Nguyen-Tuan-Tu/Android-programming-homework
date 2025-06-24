package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreenActivityAdmin extends AppCompatActivity {

    private Button accountButton, manageFoodButton, manageOrdersButton, logoutButton;
    private Switch notificationSwitch;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_admin);  // Thay thế bằng layout của bạn

        // Ánh xạ các phần tử UI
        accountButton = findViewById(R.id.account_button);
        manageFoodButton = findViewById(R.id.manage_food_button);
        manageOrdersButton = findViewById(R.id.manage_orders_button);
        logoutButton = findViewById(R.id.logout_button);
        notificationSwitch = findViewById(R.id.notification_switch);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

         //Sự kiện khi nhấn nút "Tài khoản"
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến màn hình tài khoản người dùng
                Intent intent = new Intent(MainScreenActivityAdmin.this, AccountInfoActivity.class);
                startActivity(intent);
            }
        });

        // Sự kiện khi nhấn nút "Quản lý món ăn"
        manageFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến màn hình quản lý món ăn
                Intent intent = new Intent(MainScreenActivityAdmin.this, ManageFood_Activity.class);
                startActivity(intent);
            }
        });

        // Sự kiện khi nhấn nút "Quản lý đơn hàng"
        manageOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến màn hình quản lý đơn đặt trước
                Intent intent = new Intent(MainScreenActivityAdmin.this, ManageOder_Activity.class);
                startActivity(intent);
            }
        });

        // Sự kiện khi nhấn nút "Đăng xuất"
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý đăng xuất (quay lại màn hình đăng nhập)
                Toast.makeText(MainScreenActivityAdmin.this, "Đã đăng xuất.", Toast.LENGTH_SHORT).show();
                // Quay lại màn hình đăng nhập
                Intent intent = new Intent(MainScreenActivityAdmin.this, AdminLoginActivity.class);
                startActivity(intent);
                finish();  // Kết thúc activity hiện tại
            }
        });

        // Sử dụng if-else thay cho switch cho BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                // Mở màn hình Home nếu cần
                return true;
            } else if (item.getItemId() == R.id.menu) {
                // Chuyển đến màn hình Menu
                Intent intent = new Intent(MainScreenActivityAdmin.this, MenuActivityAdmin.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.notification) {
                // Mở màn hình Notification nếu cần
                return true;
            } else if (item.getItemId() == R.id.more) {
                // Mở màn hình More nếu cần
                return true;
            }
            return false;
        });
    }
}
