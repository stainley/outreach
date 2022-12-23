package ca.appolizer.outreach.data.database;

import android.content.Context;

import androidx.room.Room;

public class DBHelper {
    private final String DB_NAME = "jobs";
    private static DBHelper instance = null;

    private DBHelper() {
    }


    public static DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    public AppDatabase createDB(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

}
