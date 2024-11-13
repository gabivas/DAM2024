package ro.ase.grupa1093;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Shaorma.class}, version = 1, exportSchema = false)
public abstract class ShaormaDB extends RoomDatabase {
    private static ShaormaDB instance;
    private static final String dbName = "shaormas.db";

    public static ShaormaDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ShaormaDB.class, dbName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract ShaormaDAO getShaormaDAO();
}
