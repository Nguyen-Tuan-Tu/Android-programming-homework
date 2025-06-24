package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat; // Import this

public class OrderActivity extends AppCompatActivity {

    // Components from topBar
    private ImageView topBarBackButton;
    private TextView topBarTitle;

    // Components from bottomNavBar
    private ImageView navHome, navList, navBell, navCart;

    // Components from activity_order.xml
    private TextView btnMinusThitBo, tvQuantityThitBo, btnPlusThitBo;
    private TextView btnMinusKhoaiTay, tvQuantityKhoaiTay, btnPlusKhoaiTay;
    private TextView btnMinusPhoMai, tvQuantityPhoMai, btnPlusPhoMai;
    private TextView btnMinusTotalQuantity, tvTotalQuantity, btnPlusTotalQuantity;
    private TextView tvTotalPrice;
    private Button btnAddtoCart;

    // Prices and counts
    private double basePrice = 150000; // Giá Burger bò
    private double priceThitBo = 15000;
    private double priceKhoaiTay = 10000;
    private double pricePhoMai = 8000;

    private int quantityThitBo = 0;
    private int quantityKhoaiTay = 0;
    private int quantityPhoMai = 0;
    private int totalQuantity = 1; // Số lượng burger tổng cộng ban đầu


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Ánh xạ các thành phần từ top bar
        topBarBackButton = findViewById(R.id.btn_back);
        topBarTitle = findViewById(R.id.toolbarTitle);

        // Set tiêu đề cho màn hình "Đặt món"
        topBarTitle.setText("Đặt món");
        // Xử lý sự kiện click cho nút back
        topBarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Quay về Activity trước đó (MainActivity)
            }
        });

        // Ánh xạ các thành phần từ bottom nav bar
        navHome = findViewById(R.id.nav_home);
        navList = findViewById(R.id.nav_list);
        navBell = findViewById(R.id.nav_bell);
        navCart = findViewById(R.id.nav_cart);

        // Highlight icon giỏ hàng vì đây là màn hình đặt món (tiếp theo của giỏ hàng)
        navCart.setColorFilter(ContextCompat.getColor(this, R.color.primary_app_color)); // Dùng màu mới


        // Ánh xạ các thành phần điều khiển số lượng và tổng tiền
        btnMinusThitBo = findViewById(R.id.btnMinusThitBo);
        tvQuantityThitBo = findViewById(R.id.tvQuantityThitBo);
        btnPlusThitBo = findViewById(R.id.btnPlusThitBo);

        btnMinusKhoaiTay = findViewById(R.id.btnMinusKhoaiTay);
        tvQuantityKhoaiTay = findViewById(R.id.tvQuantityKhoaiTay);
        btnPlusKhoaiTay = findViewById(R.id.btnPlusKhoaiTay);

        btnMinusPhoMai = findViewById(R.id.btnMinusPhoMai);
        tvQuantityPhoMai = findViewById(R.id.tvQuantityPhoMai);
        btnPlusPhoMai = findViewById(R.id.btnPlusPhoMai);

        btnMinusTotalQuantity = findViewById(R.id.btnMinusTotalQuantity);
        tvTotalQuantity = findViewById(R.id.tvTotalQuantity);
        btnPlusTotalQuantity = findViewById(R.id.btnPlusTotalQuantity);

        tvTotalPrice = findViewById(R.id.totalPrice);
        btnAddtoCart = findViewById(R.id.btnAddtoCart);

        // Set giá trị ban đầu
        tvQuantityThitBo.setText(String.valueOf(quantityThitBo));
        tvQuantityKhoaiTay.setText(String.valueOf(quantityKhoaiTay));
        tvQuantityPhoMai.setText(String.valueOf(quantityPhoMai));
        tvTotalQuantity.setText(String.valueOf(totalQuantity));
        updateTotalPrice();

        // Setup listeners cho các nút tăng/giảm số lượng topping và số lượng tổng
        setupQuantityControls(btnMinusThitBo, btnPlusThitBo, tvQuantityThitBo, "thitbo");
        setupQuantityControls(btnMinusKhoaiTay, btnPlusKhoaiTay, tvQuantityKhoaiTay, "khoaitay");
        setupQuantityControls(btnMinusPhoMai, btnPlusPhoMai, tvQuantityPhoMai, "phomai");
        setupQuantityControls(btnMinusTotalQuantity, btnPlusTotalQuantity, tvTotalQuantity, "total");


        // Xử lý sự kiện click cho nút "Thêm vào giỏ hàng"
        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tùy chọn: Chuyển đến PaymentActivity như trước, hoặc quay lại MainActivity (Giỏ hàng)
                // Hiện tại giữ nguyên chuyển đến PaymentActivity
                Intent intent = new Intent(OrderActivity.this, PaymentActivity.class);
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
            // Intent to CartActivity (not needed here if already on cart screen)
        });
    }

    // Phương thức chung để xử lý tăng/giảm số lượng
    private void setupQuantityControls(TextView minusBtn, TextView plusBtn, TextView countTv, String type) {
        minusBtn.setOnClickListener(v -> {
            int currentCount = Integer.parseInt(countTv.getText().toString());
            if (currentCount > 0) {
                currentCount--;
                countTv.setText(String.valueOf(currentCount));
                updateCounts(type, currentCount);
            }
        });

        plusBtn.setOnClickListener(v -> {
            int currentCount = Integer.parseInt(countTv.getText().toString());
            currentCount++;
            countTv.setText(String.valueOf(currentCount));
            updateCounts(type, currentCount);
        });
    }

    // Cập nhật biến số lượng và gọi tính lại tổng tiền
    private void updateCounts(String type, int newCount) {
        switch (type) {
            case "thitbo":
                quantityThitBo = newCount;
                break;
            case "khoaitay":
                quantityKhoaiTay = newCount;
                break;
            case "phomai":
                quantityPhoMai = newCount;
                break;
            case "total":
                totalQuantity = newCount;
                if (totalQuantity <= 0) totalQuantity = 1; // Đảm bảo số lượng tổng luôn ít nhất là 1
                break;
        }
        updateTotalPrice();
    }

    // Tính toán và cập nhật tổng tiền
    private void updateTotalPrice() {
        double currentTotal = (basePrice +
                (quantityThitBo * priceThitBo) +
                (quantityKhoaiTay * priceKhoaiTay) +
                (quantityPhoMai * pricePhoMai)) * totalQuantity;

        tvTotalPrice.setText(String.format("%,.0f vnđ", currentTotal));
    }
}