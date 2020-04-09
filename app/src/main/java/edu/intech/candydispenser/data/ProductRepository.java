package edu.intech.candydispenser.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.intech.candydispenser.data.models.Product;
import edu.intech.candydispenser.data.models.ProductDAO;
import edu.intech.candydispenser.data.models.ProductDatabase;

class ProductRepository {

    private ProductDAO productDao;
    private LiveData<List<Product>> allProducts;

    // Note that in order to unit test the ProductRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    ProductRepository(Application application) {
        ProductDatabase db = ProductDatabase.getDatabase(application);
        productDao = db.productDao();
        allProducts = productDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Product>> getAll() {
        return allProducts;
    }

    LiveData<Product> get(int number) {
        return productDao.get(number);
    };

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(final Product product) {
        ProductDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                productDao.insert(product);
            }
        });
    }
}
