package com.example.managerstudent_mvvm.view.addstudent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.managerstudent_mvvm.model.Student;
import com.example.managerstudent_mvvm.repository.StudentRepository;

public class AddStudentViewModel extends AndroidViewModel {
    private StudentRepository repository;

    public AddStudentViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
    }

    public void insertStudent(Student students) {
        repository.insertStudent(students);
    }

}
