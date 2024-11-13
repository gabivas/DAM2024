package ro.ase.grupa1093;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShaormaDAO {

    @Insert
    void insertShaorma(Shaorma shaorma);

    @Query("SELECT * FROM shaormas")
    List<Shaorma> getAllShaormas();

    @Delete
    void deleteShaorma(Shaorma shaorma);
}
