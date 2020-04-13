package edu.intech.candydispenser.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.intech.candydispenser.db.AppDatabase;
import edu.intech.candydispenser.db.dao.EmplacementDao;
import edu.intech.candydispenser.db.dao.ProductDao;
import edu.intech.candydispenser.db.entity.Emplacement;
import edu.intech.candydispenser.db.entity.Product;

/**
 * The type Data repository.
 */
public class DataRepository {

    private final ProductDao productDao;
    private final LiveData<List<Product>> allProducts;

    private final EmplacementDao emplacementDAO;
    private final LiveData<List<Emplacement>> allEmplacements;

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
        emplacementDAO = db.emplacementDao();
        allEmplacements = emplacementDAO.getAllEmplacements();
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
     * Gets all emplacements.
     *
     * @return the all emplacements
     */
    public LiveData<List<Emplacement>> getAllEmplacements() {
        return allEmplacements;
    }

    /**
     * Gets emplacement.
     *
     * @param id the id
     * @return the emplacement
     */
    public LiveData<Emplacement> getEmplacement(int id) {
        return  emplacementDAO.getEmplacement(id);
    }

    /**
     * Insert emplacement.
     *
     * @param emplacement the emplacement entity
     */
    public void insertEmplacement(final Emplacement emplacement) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                emplacementDAO.insertEmplacement(emplacement);
            }
        });
    }

    /**
     * Update emplacement.
     *
     * @param emplacement the emplacement entity
     */
    public void updateEmplacement(final Emplacement emplacement) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                emplacementDAO.updateEmplacement(emplacement);
            }
        });
    }
}
