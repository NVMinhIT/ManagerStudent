package com.example.managerstudent_mvvm.view.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managerstudent_mvvm.R;
import com.example.managerstudent_mvvm.model.Student;
import com.example.managerstudent_mvvm.view.adapter.StudentAdapter;
import com.example.managerstudent_mvvm.view.addstudent.AddStudentActivity;
import com.example.managerstudent_mvvm.view.editstudent.EditStudentActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListStudentActivity extends AppCompatActivity {
    public static final int ADD_EXAM_CODE = 1;
    public static final int EDIT_EXAM_CODE = 2;
    List<Student> mStudentList;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private StudentViewModel studentViewModel;
    private FloatingActionButton floatingActionButton;
    private TextView tvSex;
    private EditText edtSearch;
    private ArrayAdapter<String> arrayAdapter;
    private Spinner sp;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        initView();
        deleteItem();
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        getListStudent();
        getStudentOrderBy();
        getStudentASC();
        studentViewModel.getStudentsName.observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                studentAdapter.setStudentList(students);
            }
        });

    }

    private void getStudentASC() {
        studentViewModel.getStudentByASC().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                studentAdapter.setStudentList(students);
            }
        });
    }

    private void getStudentOrderBy() {
        studentViewModel.getStudentBy().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                studentAdapter.setStudentList(students);
            }
        });
    }

    private void getListStudent() {
        studentViewModel.getStudent().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                studentAdapter.setStudentList(students);
            }
        });
    }

    private void deleteItem() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                studentViewModel.deleStudent(studentAdapter.getStudentAt(viewHolder.getAdapterPosition()));
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if (dX < 0) {
                        Paint p = new Paint();
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX / 4, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);

                    }

                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }
        }).attachToRecyclerView(recyclerView);

        studentAdapter.setItemListener(new IOnclickItemListener<Student>() {
            @Override
            public void onClick(Student student) {
                Intent intent = new Intent(ListStudentActivity.this, EditStudentActivity.class);
                intent.putExtra("ID", student.getId());
                intent.putExtra("NAME", student.getName());
                intent.putExtra("DATE", student.getBirth());
                intent.putExtra("SEX", student.getSex());
                startActivityForResult(intent, EDIT_EXAM_CODE);
            }
        });
    }

    private void initView() {
        floatingActionButton = findViewById(R.id.fab);
        tvSex = findViewById(R.id.txt_sex);
        edtSearch = findViewById(R.id.edt_search);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (edtSearch.getText().toString().equals("")) {
                    getListStudent();

                } else {
                    studentViewModel.refresh(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
        String[] name = {"None", "sắp xếp tăng dần theo ngày", "sắp xếp giảm dần theo ngày"};
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, name);
        sp = findViewById(R.id.spinner_sort_by);
        sp.setAdapter(arrayAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:

                    case 1:
                        getStudentASC();
                        break;
                    case 2:
                        getStudentOrderBy();
                        break;
                    default:
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListStudentActivity.this, AddStudentActivity.class);
                startActivityForResult(intent, ADD_EXAM_CODE);
            }
        });
        recyclerView = findViewById(R.id.rv_list_student);
        mStudentList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentAdapter = new StudentAdapter(this, mStudentList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(studentAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_EXAM_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String name = data.getStringExtra(AddStudentActivity.EXTRA_DATA_NAME);
                String date = data.getStringExtra(AddStudentActivity.EXTRA_DATA_DATE);
                String gt = data.getStringExtra(AddStudentActivity.EXTRA_DATA_SEX);
                Student student = new Student(0, name, date, gt, "", "");
                studentViewModel.insertStudent(student);
            }
        } else if (requestCode == EDIT_EXAM_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                int id = data.getIntExtra(EditStudentActivity.EXTRA_ID, -1);
                if (id == -1) {
                    Toast.makeText(this, "Note can'tbe update", Toast.LENGTH_SHORT).show();
                }
                String name = data.getStringExtra(EditStudentActivity.EXTRA_NAME);
                String date = data.getStringExtra(EditStudentActivity.EXTRA_DATE);
                String sex = data.getStringExtra(EditStudentActivity.EXTRA_SEX);
                Student student = new Student(id, name, date, sex, "", "");
                student.setId(id);
                studentViewModel.updateStudent(student);

            }
        }
    }


}
