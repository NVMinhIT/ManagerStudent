package com.example.managerstudent_mvvm.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.managerstudent_mvvm.model.Student;
import com.example.managerstudent_mvvm.model.database.local.StudentDAO;
import com.example.managerstudent_mvvm.model.database.local.StudentDatabase;

import java.util.List;

public class StudentRepository {
    private LiveData<List<Student>> getAllStudents;
    private LiveData<List<Student>> getStudentOrderBy;
    private LiveData<List<Student>> getStudentOrderASC;
    private StudentDAO studentDAO;

    public StudentRepository(Application application) {
        StudentDatabase studentDatabase = StudentDatabase.getInstance(application);
        studentDAO = studentDatabase.studentDAO();
        getAllStudents = studentDAO.getALlStudent();
        getStudentOrderBy = studentDAO.getAllStudentOrderBy();
        getStudentOrderASC = studentDAO.getAllStudentOrderByASC();

    }

    public List<Student> getAllStudentSearch(String name) {
        return studentDAO.getStudentByName(name);
    }

    public void insertStudent(Student students) {
        new InsertStudentAsyncTask(studentDAO).execute(students);
    }

    public void deleteStudents(Student students) {
        new DeleteStudents(studentDAO).execute(students);
    }

    public void updateStudent(Student students) {
        new UpdateStudentAsyncTask(studentDAO).execute(students);
    }

    public void deleteStudent(int studentID) {
        new DeleteStudentAsyncTask(studentDAO).execute();
    }

    public LiveData<List<Student>> getAllStudentOrderBy() {
        return getStudentOrderBy;
    }
    public LiveData<List<Student>> getAllStudentOrderByASC(){
        return getStudentOrderASC;
    }
    public LiveData<List<Student>> getALlStudent() {
        return getAllStudents;
    }

    public void refresh(String searchText) {
//        String formattedSearchText = "%" + searchText + "%";
        // new GetSearchDataTask(studentDAO).execute(searchText);

    }

    /*
    - Insert Student
    - @Author: NvMinh
    */
    public static class InsertStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDAO studentDAO;

        private InsertStudentAsyncTask(StudentDAO studentDAO) {
            this.studentDAO = studentDAO;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDAO.insertStudent(students[0]);
            return null;
        }
    }

    /*
    - update Student
    - @Author: NvMinh
    */
    private static class UpdateStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDAO studentDAO;

        private UpdateStudentAsyncTask(StudentDAO studentDAO) {
            this.studentDAO = studentDAO;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDAO.updateStudent(students[0]);
            return null;
        }

    }

    private static class DeleteStudentAsyncTask extends AsyncTask<Integer, Void, Void> {
        private StudentDAO studentDAO;

        private DeleteStudentAsyncTask(StudentDAO studentDAO) {
            this.studentDAO = studentDAO;
        }


        @Override
        protected Void doInBackground(Integer... integers) {
            studentDAO.deleteAllStudent();
            return null;
        }
    }
    /*
    - DeleteStudent
    - @Author: NvMinh
    */

    private static class DeleteStudents extends AsyncTask<Student, Void, Void> {
        private StudentDAO studentDAO;

        private DeleteStudents(StudentDAO studentDAO) {
            this.studentDAO = studentDAO;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDAO.deleteStudents(students);
            return null;
        }
    }


}


