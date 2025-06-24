package com.example.login; // Đảm bảo dòng này khớp với tên package của bạn

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmEmailDkiActivity extends AppCompatActivity {

    private EditText etConfirmCode;
    private Button btnConfirmCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_dki_email);

        etConfirmCode = findViewById(R.id.etConfirmCode);
        btnConfirmCode = findViewById(R.id.btnConfirmCode);

        // Xử lý sự kiện click cho nút "Xác nhận"
        btnConfirmCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmCode();
            }
        });
    }

    private void confirmCode() {
        String code = etConfirmCode.getText().toString().trim();

        // Vẫn kiểm tra nếu trường mã xác nhận bị trống
        if (code.isEmpty()) {
            etConfirmCode.setError("Vui lòng nhập mã xác nhận");
            return; // Dừng lại nếu không có gì được nhập
        }

        // ---------- ĐOẠN CODE ĐÃ ĐƯỢC THAY ĐỔI Ở ĐÂY ----------
        // Nếu trường không trống, coi như mã xác nhận là đúng và chuyển sang màn hình thành công
        Intent intent = new Intent(ConfirmEmailDkiActivity.this, RegistrationSuccessActivity.class);
        startActivity(intent);
        finish(); // Kết thúc Activity này để người dùng không thể quay lại bằng nút back
        // ---------------------------------------------------
    }

    // Bạn có thể ghi đè onBackPressed để xử lý việc người dùng nhấn nút back tại màn hình này
    // Ví dụ: quay lại màn hình đăng ký, hoặc chỉ đóng ứng dụng
    // @Override
    // public void onBackPressed() {
    //     super.onBackPressed(); // Để quay lại màn hình trước đó (MainActivity)
    // }
}