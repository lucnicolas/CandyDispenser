package edu.intech.candydispenser.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.intech.candydispenser.db.AppDatabase;
import edu.intech.candydispenser.db.dao.EmplacementDao;
import edu.intech.candydispenser.db.dao.ProductDao;
import edu.intech.candydispenser.db.entity.EmplacementEntity;
import edu.intech.candydispenser.db.entity.ProductEntity;

public class DataRepository {

    private ProductDao productDao;
    private LiveData<List<ProductEntity>> allProducts;

    private EmplacementDao emplacementDAO;
    private LiveData<List<EmplacementEntity>> allEmplacements;

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

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<ProductEntity>> getAllProducts() {
        return allProducts;
    }

    public LiveData<ProductEntity> getProduct(int number) {
        return productDao.getProduct(number);
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertProduct(final ProductEntity productEntity) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                productDao.insertProduct(productEntity);
            }
        });
    }

    public LiveData<List<EmplacementEntity>> getAllEmplacements() { return allEmplacements; }

    public LiveData<EmplacementEntity> getEmplacement(int id) {
        return  emplacementDAO.getEmplacement(id);
    }

    public void insertEmplacement(final EmplacementEntity emplacementEntity) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                emplacementDAO.insertEmplacement(emplacementEntity);
            }
        });
    }

}
