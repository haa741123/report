// NumberBook.java
package com.example.report;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.report.AddContactActivity;
import com.example.report.Contact;
import com.example.report.DatabaseHelper;

import java.util.List;

public class NumberBook extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Contact> adapter;
    private List<Contact> contactList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        listView = findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);

        // 연락처 리스트를 가져와서 어댑터에 설정
        contactList = databaseHelper.getAllContacts();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactList);
        listView.setAdapter(adapter);

        // 리스트 아이템 클릭 시 동작
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 선택한 연락처의 상세 정보를 보여주는 화면으로 이동
                Contact selectedContact = contactList.get(position);
                // 상세 화면으로 이동하는 코드를 여기에 추가하세요.
                Intent intent = new Intent(NumberBook.this, ContactDetailActivity.class);
                intent.putExtra("contact_id", selectedContact.getId()); // 선택한 연락처의 ID를 인텐트에 추가합니다.
                startActivity(intent);
            }
        });

        // 추가 버튼 클릭 시 동작
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 현재 액티비티를 종료하고 이전 액티비티로 돌아감
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 연락처 리스트를 다시 불러와서 어댑터에 설정
        contactList.clear(); // 기존 리스트를 비워줍니다.
        contactList.addAll(databaseHelper.getAllContacts()); // 새로운 리스트로 업데이트합니다.
        adapter.notifyDataSetChanged(); // 어댑터에게 데이터 변경을 알립니다.
    }
}
