package ca.appolizer.outreach.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ca.appolizer.outreach.data.dao.JobDao;
import ca.appolizer.outreach.data.dao.StudentDao;
import ca.appolizer.outreach.data.model.Job;
import ca.appolizer.outreach.data.model.Student;

@Database(entities = {Job.class, Student.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract JobDao jobDao();
    public abstract StudentDao studentDao();
}
