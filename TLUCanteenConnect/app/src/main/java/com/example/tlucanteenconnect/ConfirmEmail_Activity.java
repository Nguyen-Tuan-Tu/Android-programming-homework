package com.example.tlucanteenconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils; // Cần thiết nếu bạn muốn kiểm tra isEmpty
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView; // Thêm import này

public class ConfirmEmail_Activity extends AppCompatActivity {

    private ImageButton btnBack;
    private EditText etConfirmCode;
    private Button btnXacNhan;
    private TextView tvConfirmErrorMessage; // Khai báo TextView lỗi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_email);

        btnBack = findViewById(R.id.btn_back_confirm_email);
        etConfirmCode = findViewById(R.id.et_confirm_code);
        btnXacNhan = findViewById(R.id.btn_xac_nhan_confirm);
        tvConfirmErrorMessage = findViewById(R.id.tv_confirm_error_message); // Ánh xạ ID

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = etConfirmCode.getText().toString().trim();
                tvConfirmErrorMessage.setVisibility(View.GONE); // Luôn ẩn lỗi cũ trước khi kiểm tra

                // Mã xác nhận auto true (như yêu cầu trước)
                // Trong ứng dụng thực tế, bạn sẽ kiểm tra mã này với server
                if (TextUtils.isEmpty(code)) { // Ví dụ: bạn vẫn có thể kiểm tra rỗng nếu muốn người dùng phải nhập gì đó
                    tvConfirmErrorMessage.setText("Vui lòng nhập mã xác nhận.");
                    tvConfirmErrorMessage.setVisibility(View.VISIBLE);
                } else {
                    Intent intent = new Intent(ConfirmEmail_Activity.this, ResetPassword_Activity.class);
                    startActivity(intent);
                }

                // *** Logic cũ dùng Toast, đã bị loại bỏ ***
                /*
                if (code.isEmpty()) {
                    Toast.makeText(ConfirmEmailActivity.this, "Vui lòng nhập mã xác nhận.", Toast.LENGTH_SHORT).show();
                } else if (code.equals("123456")) {
                    Toast.makeText(ConfirmEmailActivity.this, "Mã xác nhận đúng.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ConfirmEmailActivity.this, ResetPasswordActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ConfirmEmailActivity.this, "Mã xác nhận không đúng.", Toast.LENGTH_SHORT).show();
                }
                */
            }
        });
    }
}