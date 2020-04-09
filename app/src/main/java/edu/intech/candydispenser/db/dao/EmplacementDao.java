package edu.intech.candydispenser.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import edu.intech.candydispenser.db.entity.EmplacementEntity;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface EmplacementDao {

    @Insert(onConflict = IGNORE)
    void insertEmplacement(EmplacementEntity emplacementEntity);

    @Delete
    int removeEmplacement(EmplacementEntity emplacementEntity);

    @Query("SELECT * FROM Emplacements WHERE id = :id")
    LiveData<EmplacementEntity> getEmplacement(int id);

    @Query("SELECT * FROM Emplacements")
    LiveData<List<EmplacementEntity>> getAllEmplacements();

    @Query("DELETE FROM Emplacements")
    void deleteAllEmplacement();
}
