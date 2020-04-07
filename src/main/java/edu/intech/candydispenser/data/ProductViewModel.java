package edu.intech.candydispenser.data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.intech.candydispenser.data.ProductRepository;
import edu.intech.candydispenser.data.models.Product;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository repository;
    private LiveData<List<Product>> allProducts;

    public ProductViewModel(Application application) {
        super(application);
        repository = new ProductRepository(application);
        allProducts = repository.getAll();
    }

    public LiveData<List<Product>> getAll() {
        return allProducts;
    }

    public LiveData<Product> get(int number) {
        return repository.get(number);
    }

    public void insert(Product product) {
        repository.insert(product);
    }
}
