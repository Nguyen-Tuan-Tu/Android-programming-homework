package com.example.dat_truoc_mon_an;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

public class DatTruocMonAnActivity extends AppCompatActivity {

    private CardView cardBurgerBo;

    // Components from topBar
    private ImageView topBarBackButton;
    private TextView topBarTitle;

    // Components from bottomNavBar
    private ImageView navHome, navList, navBell, navCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_truoc_mon_an);

        // Ánh xạ các thành phần từ top bar
        topBarBackButton = findViewById(R.id.backButton);
        topBarTitle = findViewById(R.id.toolbarTitle);

        // Set tiêu đề cho màn hình "Giỏ hàng"
        topBarTitle.setText("Giỏ hàng");
        // Ẩn nút back vì đây là màn hình gốc
        topBarBackButton.setVisibility(View.GONE);

        // Ánh xạ các thành phần từ bottom nav bar
        navHome = findViewById(R.id.nav_home);
        navList = findViewById(R.id.nav_list);
        navBell = findViewById(R.id.nav_bell);
        navCart = findViewById(R.id.nav_cart);

        // Set highlight cho icon giỏ hàng vì đang ở màn hình giỏ hàng
        navCart.setColorFilter(ContextCompat.getColor(this, R.color.primary_app_color));


        cardBurgerBo = findViewById(R.id.cardBurgerBo);

        // Xử lý sự kiện click vào CardView "Burger bò"
        cardBurgerBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CHỈNH SỬA DÒNG NÀY: Chuyển sang OrderActivity thay vì PaymentActivity
                Intent intent = new Intent(DatTruocMonAnActivity.this, OrderActivity.class); // Thay đổi ở đây
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho các icon ở bottom navigation bar (tùy chọn)
        navHome.setOnClickListener(v -> {
            // Đặt màu cho icon được chọn và reset màu cho các icon khác
            navHome.setColorFilter(ContextCompat.getColor(this, R.color.primary_app_color));
            navList.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navBell.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navCart.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            // Intent to HomeActivity (if you have one)
            // startActivity(new Intent(MainActivity.this, HomeActivity.class));
        });
        navList.setOnClickListener(v -> {
            navHome.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navList.setColorFilter(ContextCompat.getColor(this, R.color.primary_app_color));
            navBell.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navCart.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            // Intent to OrderListActivity (if you have one)
        });
        navBell.setOnClickListener(v -> {
            navHome.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navList.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navBell.setColorFilter(ContextCompat.getColor(this, R.color.primary_app_color));
            navCart.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            // Intent to NotificationActivity (if you have one)
        });
        navCart.setOnClickListener(v -> {
            navHome.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navList.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navBell.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray_text));
            navCart.setColorFilter(ContextCompat.getColor(this, R.color.primary_app_color));
            // navCart.setOnClickListener is not needed here as we are already on the cart screen
        });
    }
}