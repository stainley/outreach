package ca.appolizer.outreach.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ca.appolizer.outreach.data.dto.JobDto;
import ca.appolizer.outreach.data.entity.JobEntity;

@Dao
public interface JobDao {

    @Query("SELECT * FROM  JobEntity")
    List<JobDto> getAllJobs();

    @Query("SELECT * FROM JobEntity WHERE id IN (:ids)")
    List<JobDto> getAllJobsById(int[] ids);

    @Insert
    void insertAllJobs(JobEntity... jobs);

    @Delete
    void deleteAllJobs();

    @Delete
    void deleteJob(JobEntity job);
}
