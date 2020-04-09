package edu.intech.candydispenser.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.intech.candydispenser.DataRepository;
import edu.intech.candydispenser.db.entity.ProductEntity;

public class ProductViewModel extends AndroidViewModel {

    private DataRepository repository;
    private LiveData<List<ProductEntity>> mObservableProduct;

    public ProductViewModel(Application application) {
        super(application);
        repository = new DataRepository(application);
        mObservableProduct = repository.getAll();
    }

    public LiveData<List<ProductEntity>> getAllProducts() {
        return mObservableProduct;
    }

    public LiveData<ProductEntity> getProduct(int number) {
        return repository.get(number);
    }

    public void insert(ProductEntity productEntity) {
        repository.insert(productEntity);
    }
}
