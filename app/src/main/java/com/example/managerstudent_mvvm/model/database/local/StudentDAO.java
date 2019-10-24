package com.example.managerstudent_mvvm.model.database.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.managerstudent_mvvm.model.Student;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface StudentDAO {
    @Query("SELECT * FROM student WHERE id = :Id")
    LiveData<Student> getStudentById(int Id);

    @Query("SELECT * FROM student WHERE ho_ten LIKE  :userName ")
    List<Student> getStudentByName(String userName);

    @Query("SELECT * FROM student")
    LiveData<List<Student>> getALlStudent();

    @Delete
    void deleteStudents(Student... students);

    @Insert
    void insertStudent(Student... students);

    @Query("DELETE FROM student WHERE id = :studentID")
    void deleteStudent(int studentID);

    @Query("DELETE FROM student")
    void deleteAllStudent();

    @Update(onConflict = REPLACE)
    void updateStudent(Student students);


    @Query("SELECT * FROM student ORDER BY ngay_sinh DESC")
    LiveData<List<Student>> getAllStudentOrderBy();

    @Query("SELECT * FROM student ORDER BY ngay_sinh ASC")
    LiveData<List<Student>> getAllStudentOrderByASC();

}
