package com.example.tlucanteenconnect;

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

public class ManageFood_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_managefood);

        Button Addfood = findViewById(R.id.btn_CFeditfood);
        Addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại Activity trước
                Intent intent = new Intent(ManageFood_Activity.this, AddFood_Activity.class);
                startActivity(intent);
            }
        });
        ImageView Editfood = findViewById(R.id.ic_editfood);
        Editfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại Activity trước
                Intent intent = new Intent(ManageFood_Activity.this, EditFood_Activity.class);
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