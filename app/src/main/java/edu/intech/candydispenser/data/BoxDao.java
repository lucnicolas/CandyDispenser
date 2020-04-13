package edu.intech.candydispenser.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * The interface Emplacement dao.
 */
@Dao
public interface BoxDao {

    /**
     * Insert emplacement.
     *
     * @param box the emplacement entity
     */
    @Insert(onConflict = IGNORE)
    void insertEmplacement(Box box);

    /**
     * Update emplacement.
     *
     * @param box the emplacement entity
     */
    @Update(onConflict = REPLACE)
    void updateEmplacement(Box box);

    /**
     * Remove emplacement int.
     *
     * @param box the emplacement entity
     * @return the int
     */
    @Delete
    int removeEmplacement(Box box);

    /**
     * Gets emplacement.
     *
     * @param id the id
     * @return the emplacement
     */
    @Query("SELECT * FROM Box WHERE id = :id")
    LiveData<Box> getEmplacement(int id);

    /**
     * Gets all emplacements.
     *
     * @return the all emplacements
     */
    @Query("SELECT * FROM Box")
    LiveData<List<Box>> getAllEmplacements();

    /**
     * Delete all emplacement.
     */
    @Query("DELETE FROM Box")
    void deleteAllEmplacement();
}
