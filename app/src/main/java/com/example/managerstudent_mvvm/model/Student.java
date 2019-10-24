package com.example.managerstudent_mvvm.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.managerstudent_mvvm.model.database.local.DateConverter;

@Entity(tableName = "student")
@TypeConverters(DateConverter.class)
public class Student {

    private static final String DEFAULT_PW = "123456";
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int Id;
    @ColumnInfo(name = "ho_ten")
    private String Name;
    @ColumnInfo(name = "ngay_sinh")
    private String Birth;
    @ColumnInfo(name = "gioi_tinh")
    private String Sex;
    @ColumnInfo(name = "dia_chi")
    private String Address;
    @ColumnInfo(name = "chuyen_nganh")
    private String Job;

    public Student() {

    }

    @Ignore

    public Student(int id, String name, String birth, String sex, String address, String job) {
        Id = id;
        Name = name;
        Birth = birth;
        Sex = sex;
        Address = address;
        Job = job;
    }

    public static String getDefaultPw() {
        return DEFAULT_PW;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBirth() {
        return Birth;
    }

    public void setBirth(String birth) {
        Birth = birth;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }
}
