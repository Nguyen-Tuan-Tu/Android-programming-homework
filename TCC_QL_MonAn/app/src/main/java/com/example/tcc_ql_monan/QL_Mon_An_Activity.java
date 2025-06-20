package com.example.tcc_ql_monan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QL_Mon_An_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ql_monan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.qlmonan), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button Addfood = findViewById(R.id.btn_CFeditfood);
        Addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại Activity trước
                Intent intent = new Intent(QL_Mon_An_Activity.this, Them_Mon_Activity.class);
                startActivity(intent);
            }
        });
        ImageView Editfood = findViewById(R.id.ic_editfood);
        Editfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại Activity trước
                Intent intent = new Intent(QL_Mon_An_Activity.this, Sua_TT_Mon_Activity.class);
                startActivity(intent);
            }
        });
        ImageView deleteIcon = findViewById(R.id.ic_deletefood); // hoặc bạn duyệt list item
        LinearLayout confirmBox = findViewById(R.id.layout_confirm_delete);
        Button btnYes = findViewById(R.id.btn_confirm_yes);
        Button btnNo = findViewById(R.id.btn_confirm_no);

        deleteIcon.setOnClickListener(v -> confirmBox.setVisibility(View.VISIBLE));
        btnNo.setOnClickListener(v -> confirmBox.setVisibility(View.GONE));
        btnYes.setOnClickListener(v -> {
            Toast.makeText(this, "Đã xóa món ăn", Toast.LENGTH_SHORT).show();
            confirmBox.setVisibility(View.GONE);
        });
    }
}