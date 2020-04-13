package edu.intech.candydispenser.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.intech.candydispenser.db.entity.ProductEntity;

import static androidx.room.OnConflictStrategy.ABORT;
import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * The interface Product dao.
 */
@Dao
public interface ProductDao {

    /**
     * Insert product.
     *
     * @param productEntity the product entity
     */
    @Insert(onConflict = ABORT)
    void insertProduct(ProductEntity productEntity);

    /**
     * Update product.
     *
     * @param productEntity the product entity
     */
    @Update(onConflict = REPLACE)
    void updateProduct(ProductEntity productEntity);

    /**
     * Remove product int.
     *
     * @param productEntity the product entity
     * @return the int
     */
// Return the removed product or null if it wasn't found
    @Delete
    int removeProduct(ProductEntity productEntity);

    /**
     * Gets product.
     *
     * @param number the number
     * @return the product
     */
    @Query("SELECT * FROM Products WHERE number = :number")
    LiveData<ProductEntity> getProduct(int number);

    /**
     * Gets all products.
     *
     * @return the all products
     */
    @Query("SELECT * FROM Products")
    LiveData<List<ProductEntity>> getAllProducts();

    /**
     * Delete all products.
     */
    @Query("DELETE FROM Products")
    void deleteAllProducts();
}
