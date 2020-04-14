package edu.intech.candydispenser.data.product;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

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
     * @param product the product entity
     */
    @Insert(onConflict = ABORT)
    void insertProduct(Product product);

    /**
     * Update product.
     *
     * @param product the product entity
     */
    @Update(onConflict = REPLACE)
    void updateProduct(Product product);

    /**
     * Remove product int.
     *
     * @param product the product entity
     * @return the int
     */
// Return the removed product or null if it wasn't found
    @Delete
    int removeProduct(Product product);

    /**
     * Gets product.
     *
     * @param number the number
     * @return the product
     */
    @Query("SELECT * FROM Product WHERE number = :number")
    LiveData<Product> getProduct(int number);

    /**
     * Gets all products.
     *
     * @return the all products
     */
    @Query("SELECT * FROM Product")
    LiveData<List<Product>> getAllProducts();

    /**
     * Delete all products.
     */
    @Query("DELETE FROM Product")
    void deleteAllProducts();
}
