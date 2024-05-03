package com.example.report;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.report.R;

public class InternetSearch extends AppCompatActivity {

    private WebView webView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // 웹뷰 설정
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient()); // 웹 페이지를 앱 내에서 표시하기 위해 WebViewClient 설정
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);

        // EditText 설정
        editText = findViewById(R.id.editText);

        // 웹 페이지 로드
        webView.loadUrl("https://google.com");

        // 버튼 클릭 이벤트 설정
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 현재 액티비티를 종료하고 이전 액티비티로 돌아감
            }
        });

        // 검색 버튼 클릭 이벤트 설정
        Button searchButton = findViewById(R.id.button3);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에서 입력한 값을 가져옴
                String searchText = editText.getText().toString();
                // 검색어를 URL 형식으로 변환하여 웹뷰에 로드
                webView.loadUrl("https://www.google.com/search?q=" + searchText);
            }
        });
    }
}
