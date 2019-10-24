package com.example.managerstudent_mvvm.model.database.local;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.managerstudent_mvvm.model.Student;


@Database(entities = {Student.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class StudentDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "Student_Database";
    private static StudentDatabase sStudentDatabase;
    private static RoomDatabase.Callback roomcallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
    public static synchronized StudentDatabase getInstance(Context context) {
        if (sStudentDatabase == null) {
            sStudentDatabase = Room.databaseBuilder(context.getApplicationContext(), StudentDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomcallback)
                    .build();
        }
        return sStudentDatabase;
    }

    public abstract StudentDAO studentDAO();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

}
