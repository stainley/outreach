package ca.appolizer.outreach.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ca.appolizer.outreach.data.model.Job;

@Dao
public interface JobDao {

    @Query("SELECT * FROM  Job")
    List<Job> getAllJobs();

    @Query("SELECT * FROM Job WHERE id IN (:ids)")
    List<Job> getAllJobsById(int[] ids);

    @Insert
    void insertAllJobs(Job... jobs);

    @Delete
    void deleteAllJobs();

    @Delete
    void deleteJob(Job job);
}
