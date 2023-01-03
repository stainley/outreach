package ca.appolizer.outreach.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ca.appolizer.outreach.data.dao.JobDao;
import ca.appolizer.outreach.data.database.DatabaseRoomAdapter;
import ca.appolizer.outreach.data.entity.JobEntity;
import ca.appolizer.outreach.data.mapper.JobMapper;

public final class JobRepository {

    private final DatabaseRoomAdapter databaseRoomAdapter;
    private final JobDao jobDao;


    public JobRepository(Application application) {
        databaseRoomAdapter = DatabaseRoomAdapter.getDatabase(application);
        this.jobDao = databaseRoomAdapter.jobDao();
    }

    public LiveData<List<JobEntity>> getAllJobs() {
        //TODO: Fetch from server
        //TODO: save to the database
        JobMapper jobMapper = new JobMapper();

        return jobDao.getAllJobs();
    }


}
