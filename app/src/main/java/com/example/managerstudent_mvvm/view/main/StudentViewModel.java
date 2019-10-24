package com.example.managerstudent_mvvm.view.main;


import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.managerstudent_mvvm.model.Student;
import com.example.managerstudent_mvvm.repository.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    public LiveData<List<Student>> allStudent;
    public LiveData<List<Student>> getStudentNames;
    public MutableLiveData<List<Student>> getStudentsName = new MutableLiveData<>();
    public LiveData<List<Student>> getStudentAfterOrderBy;
    public LiveData<List<Student>> getStudentAfterOrderASC;
    private StudentRepository repository;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        allStudent = repository.getALlStudent();
        getStudentAfterOrderBy = repository.getAllStudentOrderBy();
        getStudentAfterOrderASC = repository.getAllStudentOrderByASC();
    }


    public void refresh(String searchText) {
        String formattedSearchText = "%" + searchText + "%";
        new GetSearchDataTask().execute(formattedSearchText);
    }

    public void insertStudent(Student students) {
        repository.insertStudent(students);
    }

    public void deleteStudent(int studentID) {
        repository.deleteStudent(studentID);
    }

    public void updateStudent(Student students) {
        repository.updateStudent(students);
    }

    public void deleStudent(Student students) {
        repository.deleteStudents(students);
    }

    public LiveData<List<Student>> getStudent() {
        return allStudent;
    }

    public LiveData<List<Student>> getStudentBy() {
        return getStudentAfterOrderBy;
    }
    public LiveData<List<Student>> getStudentByASC() {
        return getStudentAfterOrderASC;
    }
    @SuppressLint("StaticFieldLeak")
    private class GetSearchDataTask extends AsyncTask<String, Void, List<Student>> {

        @Override
        protected List<Student> doInBackground(String... params) {
            return repository.getAllStudentSearch(params[0]);
        }

        @Override
        protected void onPostExecute(List<Student> students) {
            super.onPostExecute(students);
            getStudentsName.postValue(students); //change LiveData value
        }
    }

}
