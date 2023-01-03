package ca.appolizer.outreach.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ca.appolizer.outreach.data.dto.JobDto;
import ca.appolizer.outreach.data.entity.JobEntity;

@Dao
public interface JobDao {

    @Query("SELECT * FROM  jobs")
    LiveData<List<JobEntity>> getAllJobs();

    @Query("SELECT * FROM jobs WHERE id IN (:ids)")
    List<JobEntity> getAllJobsById(int[] ids);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllJobs(JobEntity... jobs);

    @Query("DELETE FROM jobs")
    void deleteAllJobs();

    @Delete
    void deleteJob(JobEntity job);
}
