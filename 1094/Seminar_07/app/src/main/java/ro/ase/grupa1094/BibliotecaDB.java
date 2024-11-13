package ro.ase.grupa1094;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Biblioteca.class}, version = 1, exportSchema = false)
public abstract class BibliotecaDB extends RoomDatabase {
    private static BibliotecaDB instance;
    public static final String databaseName = "biblioteci.db";

    public static BibliotecaDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, BibliotecaDB.class, databaseName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract BibliotecaDAO getBibliotecaDAO();
}
