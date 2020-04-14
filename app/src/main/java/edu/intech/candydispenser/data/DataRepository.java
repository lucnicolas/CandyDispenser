package edu.intech.candydispenser.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.intech.candydispenser.data.box.Box;
import edu.intech.candydispenser.data.box.BoxDao;
import edu.intech.candydispenser.data.product.Product;
import edu.intech.candydispenser.data.product.ProductDao;

/**
 * The type Data repository.
 */
public class DataRepository {

    private final ProductDao productDao;
    private final LiveData<List<Product>> allProducts;

    private final BoxDao boxDAO;
    private final LiveData<List<Box>> allEmplacements;

    /**
     * Instantiates a new Data repository.
     *
     * @param application the application
     */
// Note that in order to unit test the ProductRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public DataRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        productDao = db.productDao();
        allProducts = productDao.getAllProducts();
        boxDAO = db.emplacementDao();
        allEmplacements = boxDAO.getAllBoxes();
    }

    /**
     * Gets all products.
     *
     * @return the all products
     */
// Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    /**
     * Gets product.
     *
     * @param number the number
     * @return the product
     */
    public LiveData<Product> getProduct(int number) {
        return productDao.getProduct(number);
    }

    /**
     * Insert product.
     *
     * @param product the product entity
     */
// You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertProduct(final Product product) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                productDao.insertProduct(product);
            }
        });
    }

    /**
     * Update product.
     *
     * @param product the product
     */
    public void updateProduct(final Product product) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                productDao.updateProduct(product);
            }
        });
    }

    /**
     * Remove product.
     *
     * @param product the product
     */
    public void removeProduct(final Product product) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                productDao.removeProduct(product);
                boxDAO.clearBox(product.getBoxId());
            }
        });
    }

    /**
     * Gets all emplacements.
     *
     * @return the all emplacements
     */
    public LiveData<List<Box>> getAllBoxes() {
        return allEmplacements;
    }

    /**
     * Gets emplacement.
     *
     * @param id the id
     * @return the emplacement
     */
    public LiveData<Box> getBox(int id) {
        return boxDAO.getBox(id);
    }

    /**
     * Insert emplacement.
     *
     * @param box the emplacement entity
     */
    public void insertBox(final Box box) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                boxDAO.insertBox(box);
            }
        });
    }

    /**
     * Update emplacement.
     *
     * @param box the emplacement entity
     */
    public void updateBox(final Box box) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                boxDAO.updateBox(box);
            }
        });
    }
}
