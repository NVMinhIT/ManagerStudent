package com.example.managerstudent_mvvm.view.editstudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.managerstudent_mvvm.R;

public class EditStudentActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_ID = "com.example.managerstudent_mvvm.view.EXTRA_ID";
    public static final String EXTRA_NAME = "com.example.managerstudent_mvvm.view.EXTRA_NAME";
    public static final String EXTRA_DATE = "com.example.managerstudent_mvvm.view.EXTRA_DATE";
    public static final String EXTRA_SEX = "com.example.managerstudent_mvvm.view.EXTRA_SEX";
    private RadioGroup radioGroupSex;
    private RadioButton radioButton;
    private EditText edtNameUpdate;
    private EditText edtDateUpdate;
    private Button btnSave;
    private ImageButton imageButtonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        initView();
    }

    private void initView() {
        imageButtonBack = findViewById(R.id.imb_back_update);
        imageButtonBack.setOnClickListener(this);
        edtNameUpdate = findViewById(R.id.edt_update_name);
        edtDateUpdate = findViewById(R.id.edt_update_birth);
        radioGroupSex = findViewById(R.id.rdg_sex_update);
        btnSave = findViewById(R.id.bt_save_update);
        Intent da = getIntent();
        String ten = da.getStringExtra("NAME");
        edtNameUpdate.setText(ten);
        String ngay = da.getStringExtra("DATE");
        edtDateUpdate.setText(ngay);
        String gt = da.getStringExtra("SEX");
        if (gt != null && gt.equals("Nam")) {
            radioGroupSex.check(R.id.rd_nam_update);
        } else if (gt != null && gt.equals("Nữ")) {
            radioGroupSex.check(R.id.rd_nu_update);
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtNameUpdate.getText().toString();
                String date = edtDateUpdate.getText().toString();
                int radioId = radioGroupSex.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String sex = radioButton.getText().toString();
                int id = getIntent().getIntExtra("ID", -1);
                Intent dt = new Intent();
                if (id != -1) {
                    dt.putExtra(EXTRA_ID, id);
                }
                dt.putExtra(EXTRA_NAME, name);
                dt.putExtra(EXTRA_DATE, date);
                dt.putExtra(EXTRA_SEX, sex);
                setResult(RESULT_OK, dt);
                finish();
            }
        });

    }
    @Override
    public void onClick(View view) {
        finish();
    }
}
