package edu.intech.candydispenser.data.box;

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
 * The interface Box dao.
 */
@Dao
public interface BoxDao {

    /**
     * Insert emplacement.
     *
     * @param box the box
     */
    @Insert(onConflict = IGNORE)
    void insertBox(Box box);

    /**
     * Update emplacement.
     *
     * @param box the box
     */
    @Update(onConflict = REPLACE)
    void updateBox(Box box);

    /**
     * Remove emplacement int.
     *
     * @param box the box
     * @return the int
     */
    @Delete
    int removeBox(Box box);

    /**
     * Gets emplacement.
     *
     * @param id the id
     * @return the emplacement
     */
    @Query("SELECT * FROM Box WHERE id = :id")
    LiveData<Box> getBox(int id);

    /**
     * Gets all emplacements.
     *
     * @return the all emplacements
     */
    @Query("SELECT * FROM Box")
    LiveData<List<Box>> getAllBoxes();

    /**
     * Delete all emplacement.
     */
    @Query("DELETE FROM Box")
    void deleteAllBoxes();

    /**
     * Is free boolean.
     *
     * @param productId the product id
     * @return the boolean
     */
    @Query("SELECT NOT EXISTS(SELECT 1 FROM Box WHERE productId = :productId LIMIT 1)")
    Boolean isFree(int productId);
}