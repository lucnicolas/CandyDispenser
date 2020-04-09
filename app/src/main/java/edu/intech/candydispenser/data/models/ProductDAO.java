package edu.intech.candydispenser.data.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface ProductDAO {

    @Insert(onConflict = IGNORE)
    void insertProduct(Product product);

    @Update(onConflict = IGNORE)
    void updateProduct(Product product);

    // Return the removed product or null if it wasn't found
    @Delete
    int removeProduct(Product product);

    @Query("SELECT * FROM Products WHERE number = :number")
    LiveData<Product> getProduct(int number);

    @Query("SELECT * FROM Products")
    LiveData<List<Product>> getAllProducts();

    @Query("DELETE FROM Products")
    void deleteAllProducts();
}
