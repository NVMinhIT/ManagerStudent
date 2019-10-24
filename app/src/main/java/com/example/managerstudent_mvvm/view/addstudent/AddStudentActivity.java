package com.example.managerstudent_mvvm.view.addstudent;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.managerstudent_mvvm.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_ID_NAME = "com.example.managerstudent_mvvm.view.EXTRA_ID_NAME";
    public static final String EXTRA_DATA_NAME = "com.example.managerstudent_mvvm.view.EXTRA_DATA_NAME";
    public static final String EXTRA_DATA_DATE = "com.example.managerstudent_mvvm.view.EXTRA_DATA_DATE";
    public static final String EXTRA_DATA_SEX = "com.example.managerstudent_mvvm.view.EXTRA_DATA_SEX";
    private TextView tvDate;
    private RadioGroup radioGroupSex;
    private RadioButton radioButton;
    private Button btSave;
    private ImageButton btBack;
    private EditText edtName, edtAddress;
    private EditText edtJob;
    private Calendar calendar;
    private String dateString;
    private AddStudentViewModel addStudentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        calendar = Calendar.getInstance();
        addStudentViewModel = ViewModelProviders.of(this).get(AddStudentViewModel.class);

        initView();
    }

    private void initView() {
        tvDate = findViewById(R.id.txt_date);
        tvDate.setOnClickListener(this);
        edtName = findViewById(R.id.edt_tensv);
        radioGroupSex = findViewById(R.id.rdg_sex);
        btSave = findViewById(R.id.btn_save);
        btSave.setOnClickListener(this);
        edtAddress = findViewById(R.id.edt_themdiachi);
        edtJob = findViewById(R.id.edt_themchuyennganh);
        btBack = findViewById(R.id.imb_back);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showDialogDate() {
        final Calendar calendar = Calendar.getInstance();
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int dayOfMonth, int month, int year) {
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                calendar.set(dayOfMonth, month, year);
                dateString = sdf.format(calendar.getTimeInMillis());
                tvDate.setText(dateString);

                Log.d("hihi", "date" + tvDate);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_date:
                showDialogDate();
                break;
            case R.id.btn_save:
                String name = edtName.getText().toString();
                String address = edtAddress.getText().toString();
                String dates = tvDate.getText().toString();
                int radioId = radioGroupSex.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String sex = radioButton.getText().toString();

                if (name.trim().isEmpty() || dates.trim().isEmpty()) {
                    Toast.makeText(this, "Please enter name and date", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent data = new Intent();
                data.putExtra(EXTRA_DATA_NAME, name);
                data.putExtra(EXTRA_DATA_DATE, dates);
                data.putExtra(EXTRA_DATA_SEX, sex);
                setResult(RESULT_OK, data);
                finish();

        }
    }
}
