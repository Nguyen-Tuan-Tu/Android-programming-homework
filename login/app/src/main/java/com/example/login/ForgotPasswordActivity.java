package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button confirmButton;
    private ImageButton backButton;

    private FirebaseDatabase database;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = findViewById(R.id.et_email);
        confirmButton = findViewById(R.id.btn_confirm);
        backButton = findViewById(R.id.btn_back);

        // Khởi tạo Firebase Realtime Database
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");

        // Sự kiện nhấn nút Back
        backButton.setOnClickListener(v -> onBackPressed());

        confirmButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(ForgotPasswordActivity.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            } else {
                // Kiểm tra email có tồn tại trong Firebase
                checkEmailExistsInFirebase(email);
            }
        });
    }

    private void checkEmailExistsInFirebase(String email) {
        // Lắng nghe dữ liệu từ Firebase để kiểm tra email
        usersRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Nếu email tồn tại trong Firebase, chuyển sang màn hình nhập mã OTP
                    Toast.makeText(ForgotPasswordActivity.this, "Email hợp lệ, chuyển sang màn hình OTP", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ForgotPasswordActivity.this, VerificationCodeActivity.class);
                    intent.putExtra("email", email);  // Truyền email qua màn hình OTP
                    startActivity(intent);
                } else {
                    // Nếu email không tồn tại, yêu cầu nhập lại email
                    Toast.makeText(ForgotPasswordActivity.this, "Email không tồn tại, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ForgotPasswordActivity.this, "Lỗi khi kết nối đến Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
