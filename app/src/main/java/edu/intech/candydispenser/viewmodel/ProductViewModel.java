package edu.intech.candydispenser.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.intech.candydispenser.db.entity.ProductEntity;
import edu.intech.candydispenser.model.DataRepository;

public class ProductViewModel extends AndroidViewModel {

    private final DataRepository repository;
    private final LiveData<List<ProductEntity>> mObservableProduct;

    public ProductViewModel(Application application) {
        super(application);
        repository = new DataRepository(application);
        mObservableProduct = repository.getAllProducts();
    }

    public LiveData<List<ProductEntity>> getAllProducts() {
        return mObservableProduct;
    }

    public LiveData<ProductEntity> getProduct(int number) {
        return repository.getProduct(number);
    }

    public void insert(ProductEntity productEntity) {
        repository.insertProduct(productEntity);
    }
}
