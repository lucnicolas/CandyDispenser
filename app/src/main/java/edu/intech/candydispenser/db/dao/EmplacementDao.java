package edu.intech.candydispenser.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.intech.candydispenser.db.entity.Emplacement;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * The interface Emplacement dao.
 */
@Dao
public interface EmplacementDao {

    /**
     * Insert emplacement.
     *
     * @param emplacement the emplacement entity
     */
    @Insert(onConflict = IGNORE)
    void insertEmplacement(Emplacement emplacement);

    /**
     * Update emplacement.
     *
     * @param emplacement the emplacement entity
     */
    @Update(onConflict = REPLACE)
    void updateEmplacement(Emplacement emplacement);

    /**
     * Remove emplacement int.
     *
     * @param emplacement the emplacement entity
     * @return the int
     */
    @Delete
    int removeEmplacement(Emplacement emplacement);

    /**
     * Gets emplacement.
     *
     * @param id the id
     * @return the emplacement
     */
    @Query("SELECT * FROM Emplacement WHERE id = :id")
    LiveData<Emplacement> getEmplacement(int id);

    /**
     * Gets all emplacements.
     *
     * @return the all emplacements
     */
    @Query("SELECT * FROM Emplacement")
    LiveData<List<Emplacement>> getAllEmplacements();

    /**
     * Delete all emplacement.
     */
    @Query("DELETE FROM Emplacement")
    void deleteAllEmplacement();
}
