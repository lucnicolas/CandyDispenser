package edu.intech.candydispenser.data.box;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
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

    //@Query("SELECT NOT EXISTS(SELECT 1 FROM Box WHERE productId = :productId LIMIT 1)")
    //Boolean isFree(int productId);

    /**
     * Clear box.
     *
     * @param id the id
     */
    @Query("UPDATE Box SET product_name = '' WHERE id = :id ")
    void clearBox(int id);

    @Query("DELETE FROM Box WHERE id = :id")
    void deleteLastBox(int id);
}