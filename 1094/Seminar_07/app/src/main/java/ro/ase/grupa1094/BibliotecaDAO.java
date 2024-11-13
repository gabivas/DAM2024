package ro.ase.grupa1094;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BibliotecaDAO {

    @Insert
    void insertBiblioteca(Biblioteca biblioteca);

    @Query("SELECT * FROM biblioteci")
    List<Biblioteca> getBiblioteci();
}
