package edu.intech.candydispenser.data.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface EmplacementDAO {

    @Insert(onConflict = IGNORE)
    void insertEmplacement(Emplacement emplacement);

    @Delete
    int removeEmplacement(Emplacement emplacement);

    @Query("SELECT * FROM Emplacements WHERE id = :id")
    LiveData<Emplacement> getEmplacement(int id);

    @Query("SELECT * FROM Emplacements")
    LiveData<List<Emplacement>> getAllEmplacement();

    @Query("DELETE FROM Emplacements")
    void deleteAllEmplacement();
}
