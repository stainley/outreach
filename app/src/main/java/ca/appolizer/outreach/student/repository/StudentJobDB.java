package ca.appolizer.outreach.student.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ca.appolizer.outreach.model.response.JobApplied;

public class StudentJobDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "jobs.db";
    private static final int DB_VERSION = 1;
    private static final String TBL_NAME = "MY_JOB";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_USER_ID = "USER_ID";
    private static final String COLUMN_JOB_ID = "JOB_ID";
    private static final String COLUMN_USER_EMAIL = "USER_EMAIL";

    public StudentJobDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String db_creation = "CREATE TABLE " + TBL_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_ID + " INTEGER," + COLUMN_JOB_ID + " INTEGER, " + COLUMN_USER_EMAIL +" VARCHAR)";

        db.execSQL(db_creation);
    }

    public void applyJob(long userId, long jobId, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_JOB_ID, jobId);
        values.put(COLUMN_USER_EMAIL, email);

        db.insert(TBL_NAME, null, values);
        db.close();
    }

    public JobApplied getJob(long userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlFindUser = "SELECT * FROM " + TBL_NAME + " WHERE " + COLUMN_USER_ID + " = \"" + userId + "\"";
        Cursor cursor = db.rawQuery(sqlFindUser, null);
        cursor.moveToFirst();

        JobApplied jobApplied = new JobApplied(cursor.getLong(1), cursor.getLong(2), cursor.getString(3));
        cursor.close();
        return jobApplied;
    }

    public List<JobApplied> getJobs() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlFindUser = "SELECT * FROM " + TBL_NAME;
        Cursor cursor = db.rawQuery(sqlFindUser, null);
        List<JobApplied> jobApplieds = new ArrayList<>();


        if (cursor.moveToFirst()) {
            do {
                JobApplied jobApplied = new JobApplied(cursor.getLong(1), cursor.getLong(2), cursor.getString(3));
                jobApplieds.add(jobApplied);
            } while (cursor.moveToNext());
        }
        db.close();
        return jobApplieds;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
