package ca.appolizer.outreach.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import ca.appolizer.outreach.data.dto.StudentDto;

@Dao
public interface StudentDao {

    @Insert
    void insertStudent(StudentDto studentDto);

    @Delete
    void deleteStudents();

    @Delete
    void deleteStudent(StudentDto studentDto);

    @Query("SELECT * FROM StudentDto")
    StudentDto getStudentInfo();
}
