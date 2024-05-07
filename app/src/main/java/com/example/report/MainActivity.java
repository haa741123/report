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

        // 첫 번째 버튼을 찾습니다.
        TextView IntroduceButton = findViewById(R.id.phonestate1);
        // 첫 번째 버튼에 클릭 리스너를 추가합니다.s
        IntroduceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫 번째 버튼을 클릭했을 때 실행할 코드를 작성합니다.
                Intent intent = new Intent(MainActivity.this, NumberBook.class);
                startActivity(intent);
            }
        });

        // 두 번째 버튼을 찾습니다.
        TextView authorButton = findViewById(R.id.author2);
        // 두 번째 버튼에 클릭 리스너를 추가합니다.
        authorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 두 번째 버튼을 클릭했을 때 실행할 코드를 작성합니다.
                Intent intent = new Intent(MainActivity.this, Introduce.class);
                startActivity(intent);
            }
        });

        TextView InternetButton = findViewById(R.id.author1);
        // 두 번째 버튼에 클릭 리스너를 추가합니다.
        InternetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 두 번째 버튼을 클릭했을 때 실행할 코드를 작성합니다.
                Intent intent = new Intent(MainActivity.this, InternetSearch.class);
                startActivity(intent);
            }
        });

        // 첫 번째 버튼을 찾습니다.
        TextView phonestateButton = findViewById(R.id.phonestate2);
        // 첫 번째 버튼에 클릭 리스너를 추가합니다.
        phonestateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫 번째 버튼을 클릭했을 때 실행할 코드를 작성합니다.
                Intent intent = new Intent(MainActivity.this, PhoneStat.class);
                startActivity(intent);
            }
        });
    }
}

