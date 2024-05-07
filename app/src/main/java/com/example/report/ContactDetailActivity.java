package com.example.report;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailActivity extends AppCompatActivity {

    private int contactId;
    private DatabaseHelper databaseHelper;
    private EditText editTextName, editTextPhoneNumber, editTextEmail, editTextWorkplaceName, editTextJobTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        // EditText fields
        editTextName = findViewById(R.id.editTextName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextWorkplaceName = findViewById(R.id.editTextWorkplaceName);
        editTextJobTitle = findViewById(R.id.editTextJobTitle);

        // Save button
        Button buttonSave = findViewById(R.id.buttonSave);
        // Delete button
        Button buttonDelete = findViewById(R.id.buttonDelete);
        // Share button
        Button buttonShare = findViewById(R.id.buttonShare);

        // Intent에서 연락처의 ID를 가져옵니다.
        contactId = getIntent().getIntExtra("contact_id", -1);

        // 데이터베이스 헬퍼 초기화
        databaseHelper = new DatabaseHelper(this);

        // 해당 ID를 가지고 있는 연락처를 찾습니다.
        Contact contact = databaseHelper.getContact(contactId);

        // 불러온 연락처 정보를 EditText에 설정합니다.
        if (contact != null) {
            editTextName.setText(contact.getName());
            editTextPhoneNumber.setText(contact.getPhoneNumber());
            editTextEmail.setText(contact.getEmail());
            editTextWorkplaceName.setText(contact.getWorkplaceName());
            editTextJobTitle.setText(contact.getJobTitle());
        }

        // 저장 버튼 클릭 시 동작 설정
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 변경된 정보를 가져옵니다.
                String name = editTextName.getText().toString().trim();
                String phoneNumber = editTextPhoneNumber.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String workplaceName = editTextWorkplaceName.getText().toString().trim();
                String jobTitle = editTextJobTitle.getText().toString().trim();

                // Contact 객체를 생성하여 수정된 정보를 설정합니다.
                Contact updatedContact = new Contact(contactId, name, phoneNumber, email, workplaceName, jobTitle);

                // 데이터베이스에 업데이트합니다.
                databaseHelper.updateContact(updatedContact);

                // 수정된 정보를 나타내는 Intent를 반환합니다.
                Intent intent = new Intent();
                intent.putExtra("updated_contact_id", updatedContact.getId());
                intent.putExtra("updated_contact_name", updatedContact.getName());
                intent.putExtra("updated_contact_phone", updatedContact.getPhoneNumber());
                intent.putExtra("updated_contact_email", updatedContact.getEmail());
                intent.putExtra("updated_contact_workplace", updatedContact.getWorkplaceName());
                intent.putExtra("updated_contact_job", updatedContact.getJobTitle());
                setResult(RESULT_OK, intent);

                // 액티비티를 종료합니다.
                finish();
            }
        });

        // 삭제 버튼 클릭 시 동작 설정
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이터베이스에서 연락처를 삭제합니다.
                databaseHelper.deleteContact(contactId);
                // 삭제 성공 메시지 표시
                Toast.makeText(ContactDetailActivity.this, "연락처가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                // 액티비티 종료
                finish();
            }
        });

        // 공유 버튼 클릭 시 동작 설정
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 연락처 정보를 문자열로 가져옵니다.
                String contactInfo = "이름: " + editTextName.getText().toString().trim() + "\n" +
                        "전화번호: " + editTextPhoneNumber.getText().toString().trim() + "\n" +
                        "이메일: " + editTextEmail.getText().toString().trim() + "\n" +
                        "직장명: " + editTextWorkplaceName.getText().toString().trim() + "\n" +
                        "직급: " + editTextJobTitle.getText().toString().trim();

                // 공유 Intent 생성
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, contactInfo);
                startActivity(Intent.createChooser(shareIntent, "연락처 공유하기"));
            }
        });
    }
}
