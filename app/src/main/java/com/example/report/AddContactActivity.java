package com.example.report;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {
    private EditText editTextName, editTextPhoneNumber, editTextEmail, editTextWorkplace, editTextJobTitle;
    private Button buttonSave, buttonCancel;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editTextName = findViewById(R.id.editTextName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextWorkplace = findViewById(R.id.editTextWorkplace);
        editTextJobTitle = findViewById(R.id.editTextJobTitle);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);
        databaseHelper = new DatabaseHelper(this);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력한 정보를 가져와서 데이터베이스에 추가
                String name = editTextName.getText().toString().trim();
                String phoneNumber = editTextPhoneNumber.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String workplace = editTextWorkplace.getText().toString().trim();
                String jobTitle = editTextJobTitle.getText().toString().trim();

                // 새로운 연락처 객체 생성
                Contact newContact = new Contact();
                newContact.setName(name);
                newContact.setPhoneNumber(phoneNumber);
                newContact.setEmail(email);
                newContact.setWorkplaceName(workplace);
                newContact.setJobTitle(jobTitle);

                // 데이터베이스에 추가
                databaseHelper.addContact(newContact);

                // 액티비티 종료
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 액티비티 종료
                finish();
            }
        });
    }
}
