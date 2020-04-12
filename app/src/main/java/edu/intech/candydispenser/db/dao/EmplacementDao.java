package edu.intech.candydispenser.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import edu.intech.candydispenser.db.entity.EmplacementEntity;

import static androidx.room.OnConflictStrategy.ABORT;

/**
 * The interface Emplacement dao.
 */
@Dao
public interface EmplacementDao {

    /**
     * Insert emplacement.
     *
     * @param emplacementEntity the emplacement entity
     */
    @Insert(onConflict = ABORT)
    void insertEmplacement(EmplacementEntity emplacementEntity);

    /**
     * Remove emplacement int.
     *
     * @param emplacementEntity the emplacement entity
     * @return the int
     */
    @Delete
    int removeEmplacement(EmplacementEntity emplacementEntity);

    /**
     * Gets emplacement.
     *
     * @param id the id
     * @return the emplacement
     */
    @Query("SELECT * FROM Emplacements WHERE id = :id")
    LiveData<EmplacementEntity> getEmplacement(int id);

    /**
     * Gets all emplacements.
     *
     * @return the all emplacements
     */
    @Query("SELECT * FROM Emplacements")
    LiveData<List<EmplacementEntity>> getAllEmplacements();

    /**
     * Delete all emplacement.
     */
    @Query("DELETE FROM Emplacements")
    void deleteAllEmplacement();
}
