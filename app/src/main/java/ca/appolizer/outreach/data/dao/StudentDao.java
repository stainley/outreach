package ca.appolizer.outreach.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import ca.appolizer.outreach.data.model.Student;

@Dao
public interface StudentDao {

    @Insert
    void insertStudent(Student student);

    @Delete
    void deleteStudents();

    @Delete
    void deleteStudent(Student student);

    @Query("SELECT * FROM Student")
    Student getStudentInfo();
}
