package net.justdave.nwsweatheralertswidget;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NWSDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "nwswidgets.db";
    private static final int DATABASE_VERSION = 2;

    public NWSDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE nwswidgets ("
                + "row_id INTEGER PRIMARY KEY, "
                + "widget_id INTEGER NOT NULL, "
                + "state TEXT NOT NULL, "
                + "county TEXT NOT NULL, "
                + "uri TEXT NOT NULL"
                + ");");
        database.execSQL("CREATE TABLE nwsalertcache ("
                + ""
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(NWSDatabase.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion);
        // if we ever actually upgrade the schema, we can do stuff here
        if (oldVersion == 0) {
            db.execSQL("DROP TABLE IF EXISTS nwsalertcache");
            db.execSQL("DROP TABLE IF EXISTS nwswidgets");
            onCreate(db);
        }
    }
}
