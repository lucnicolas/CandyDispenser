package edu.intech.candydispenser.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.intech.candydispenser.db.entity.Product;
import edu.intech.candydispenser.model.DataRepository;

/**
 * The type Product view model.
 */
public class ProductViewModel extends AndroidViewModel {

    private final DataRepository repository;
    private final LiveData<List<Product>> mObservableProduct;

    /**
     * Instantiates a new Product view model.
     *
     * @param application the application
     */
    public ProductViewModel(Application application) {
        super(application);
        repository = new DataRepository(application);
        mObservableProduct = repository.getAllProducts();
    }

    /**
     * Gets all products.
     *
     * @return the all products
     */
    public LiveData<List<Product>> getAllProducts() {
        return mObservableProduct;
    }

    /**
     * Gets product.
     *
     * @param number the number
     * @return the product
     */
    public LiveData<Product> getProduct(int number) {
        return repository.getProduct(number);
    }

    /**
     * Insert.
     *
     * @param product the product entity
     */
    public void insert(Product product) {
        repository.insertProduct(product);
    }
}
