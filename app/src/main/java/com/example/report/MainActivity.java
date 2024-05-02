package com.example.report;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 버튼을 찾습니다.
        TextView phonestateButton = findViewById(R.id.phonestate1);

        // 버튼에 클릭 리스너를 추가합니다.
        phonestateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent를 사용하여 activity_main2로 이동합니다.
                Intent intent = new Intent(MainActivity.this, NumberBook.class);
                startActivity(intent);
            }
        });
    }
}
