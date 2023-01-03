package ca.appolizer.outreach.data.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ca.appolizer.outreach.data.dao.JobDao;
import ca.appolizer.outreach.data.entity.JobEntity;

@Database(entities = {JobEntity.class}, version = 1, exportSchema = false)
public abstract class DatabaseRoomAdapter extends RoomDatabase {
    private final static String DB_NAME = "outreach_db";

    public abstract JobDao jobDao();

    DatabaseRoomAdapter(){}
    public static DatabaseRoomAdapter getDatabase(Application application) {
        return Room.databaseBuilder(application, DatabaseRoomAdapter.class, DB_NAME).allowMainThreadQueries().build();
    }

}
