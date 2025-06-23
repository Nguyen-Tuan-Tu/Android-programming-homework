package com.example.chi_tiet_mon_an; // Đảm bảo đúng package name của bạn

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // Giữ lại nếu bạn vẫn dùng Button ở đâu đó (ví dụ: addToCartButton)
import android.widget.ImageView;
import android.widget.TextView; // Đảm bảo import TextView

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class ChiTietMonAnActivity extends AppCompatActivity {

    // Thay đổi từ Button sang TextView cho các nút cộng/trừ topping
    private TextView btnMinusThitBo, btnPlusThitBo;
    private TextView tvQuantityThitBo;
    private TextView tvPriceThitBo;

    private TextView btnMinusKhoaiTay, btnPlusKhoaiTay;
    private TextView tvQuantityKhoaiTay;
    private TextView tvPriceKhoaiTay;

    private TextView btnMinusPhoMai, btnPlusPhoMai;
    private TextView tvQuantityPhoMai;
    private TextView tvPricePhoMai;

    // Thay đổi từ Button sang TextView cho các nút cộng/trừ tổng số lượng
    private TextView btnMinusTotalQuantity, btnPlusTotalQuantity;
    private TextView tvTotalQuantity;

    private Button addToCartButton; // Cái này vẫn là Button
    private ImageView backButton;
    private TextView totalPrice;

    private int quantityThitBo = 0;
    private int quantityKhoaiTay = 0;
    private int quantityPhoMai = 0;
    private int totalFoodQuantity = 1;

    // Giá cơ bản của Burger bò
    private final double baseBurgerPrice = 150000.0;

    // Giá topping (ví dụ)
    private final double priceThitBo = 15000.0;
    private final double priceKhoaiTay = 10000.0;
    private final double pricePhoMai = 8000.0;

    // Định dạng tiền tệ
    private NumberFormat currencyFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon_an);

        // Khởi tạo định dạng tiền tệ
        currencyFormatter = NumberFormat.getInstance(new Locale("vi", "VN"));

        // Ánh xạ các View từ layout (giờ là TextView thay vì Button)
        btnMinusThitBo = findViewById(R.id.btnMinusThitBo);
        btnPlusThitBo = findViewById(R.id.btnPlusThitBo);
        tvQuantityThitBo = findViewById(R.id.tvQuantityThitBo);
        tvPriceThitBo = findViewById(R.id.tvPriceThitBo);

        btnMinusKhoaiTay = findViewById(R.id.btnMinusKhoaiTay);
        btnPlusKhoaiTay = findViewById(R.id.btnPlusKhoaiTay);
        tvQuantityKhoaiTay = findViewById(R.id.tvQuantityKhoaiTay);
        tvPriceKhoaiTay = findViewById(R.id.tvPriceKhoaiTay);

        btnMinusPhoMai = findViewById(R.id.btnMinusPhoMai);
        btnPlusPhoMai = findViewById(R.id.btnPlusPhoMai);
        tvQuantityPhoMai = findViewById(R.id.tvQuantityPhoMai);
        tvPricePhoMai = findViewById(R.id.tvPricePhoMai);

        btnMinusTotalQuantity = findViewById(R.id.btnMinusTotalQuantity);
        btnPlusTotalQuantity = findViewById(R.id.btnPlusTotalQuantity);
        tvTotalQuantity = findViewById(R.id.tvTotalQuantity);

        addToCartButton = findViewById(R.id.addToCartButton);
        backButton = findViewById(R.id.backButton);
        totalPrice = findViewById(R.id.totalPrice);

        // Cập nhật UI ban đầu (bao gồm cả giá topping)
        updateQuantitiesUI();
        updateTotalPrice();
        displayToppingPrices();

        // Xử lý sự kiện cho các nút cộng/trừ topping (logic giữ nguyên)
        btnPlusThitBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityThitBo++;
                updateQuantitiesUI();
                updateTotalPrice();
            }
        });
        btnMinusThitBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityThitBo > 0) {
                    quantityThitBo--;
                    updateQuantitiesUI();
                    updateTotalPrice();
                }
            }
        });

        btnPlusKhoaiTay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityKhoaiTay++;
                updateQuantitiesUI();
                updateTotalPrice();
            }
        });
        btnMinusKhoaiTay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityKhoaiTay > 0) {
                    quantityKhoaiTay--;
                    updateQuantitiesUI();
                    updateTotalPrice();
                }
            }
        });

        btnPlusPhoMai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityPhoMai++;
                updateQuantitiesUI();
                updateTotalPrice();
            }
        });
        btnMinusPhoMai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityPhoMai > 0) {
                    quantityPhoMai--;
                    updateQuantitiesUI();
                    updateTotalPrice();
                }
            }
        });

        // Xử lý sự kiện cho số lượng món ăn chính
        btnPlusTotalQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalFoodQuantity++;
                updateQuantitiesUI();
                updateTotalPrice();
            }
        });
        btnMinusTotalQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalFoodQuantity > 1) { // Đảm bảo số lượng món ăn ít nhất là 1
                    totalFoodQuantity--;
                    updateQuantitiesUI();
                    updateTotalPrice();
                }
            }
        });

        // Xử lý sự kiện khi click nút "Thêm vào giỏ hàng"
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddToCartSuccessDialog();
            }
        });

        // Xử lý sự kiện khi click nút "Quay lại"
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Quay lại màn hình trước đó
            }
        });
    }

    private void displayToppingPrices() {
        tvPriceThitBo.setText("+ " + currencyFormatter.format(priceThitBo) + "đ");
        tvPriceKhoaiTay.setText("+ " + currencyFormatter.format(priceKhoaiTay) + "đ");
        tvPricePhoMai.setText("+ " + currencyFormatter.format(pricePhoMai) + "đ");
    }

    private void updateQuantitiesUI() {
        tvQuantityThitBo.setText(String.valueOf(quantityThitBo));
        tvQuantityKhoaiTay.setText(String.valueOf(quantityKhoaiTay));
        tvQuantityPhoMai.setText(String.valueOf(quantityPhoMai));
        tvTotalQuantity.setText(String.valueOf(totalFoodQuantity));
    }

    private void updateTotalPrice() {
        double currentPrice = (baseBurgerPrice +
                (quantityThitBo * priceThitBo) +
                (quantityKhoaiTay * priceKhoaiTay) +
                (quantityPhoMai * pricePhoMai)) * totalFoodQuantity;

        String formattedPrice = currencyFormatter.format(currentPrice) + " vnđ";
        totalPrice.setText(formattedPrice);
    }

    private void showAddToCartSuccessDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_to_cart_success);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        Button confirmButton = dialog.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                resetSelections();
            }
        });

        dialog.show();
    }

    private void resetSelections() {
        quantityThitBo = 0;
        quantityKhoaiTay = 0;
        quantityPhoMai = 0;
        totalFoodQuantity = 1;
        updateQuantitiesUI();
        updateTotalPrice();
    }
}