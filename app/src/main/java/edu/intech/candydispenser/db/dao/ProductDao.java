package edu.intech.candydispenser.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.intech.candydispenser.db.entity.ProductEntity;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface ProductDao {

    @Insert(onConflict = IGNORE)
    void insertProduct(ProductEntity productEntity);

    @Update(onConflict = IGNORE)
    void updateProduct(ProductEntity productEntity);

    // Return the removed product or null if it wasn't found
    @Delete
    int removeProduct(ProductEntity productEntity);

    @Query("SELECT * FROM Products WHERE number = :number")
    LiveData<ProductEntity> getProduct(int number);

    @Query("SELECT * FROM Products")
    LiveData<List<ProductEntity>> getAllProducts();

    @Query("DELETE FROM Products")
    void deleteAllProducts();
}
