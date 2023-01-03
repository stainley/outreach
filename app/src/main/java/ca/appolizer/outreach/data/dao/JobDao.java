package ca.appolizer.outreach.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ca.appolizer.outreach.data.dto.JobDto;

@Dao
public interface JobDao {

    @Query("SELECT * FROM  Job")
    List<JobDto> getAllJobs();

    @Query("SELECT * FROM Job WHERE id IN (:ids)")
    List<JobDto> getAllJobsById(int[] ids);

    @Insert
    void insertAllJobs(JobDto... jobDtos);

    @Delete
    void deleteAllJobs();

    @Delete
    void deleteJob(JobDto jobDto);
}
