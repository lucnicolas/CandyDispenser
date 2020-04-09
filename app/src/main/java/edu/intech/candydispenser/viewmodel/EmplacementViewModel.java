package edu.intech.candydispenser.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.intech.candydispenser.model.DataRepository;
import edu.intech.candydispenser.db.entity.EmplacementEntity;

public class EmplacementViewModel extends AndroidViewModel {

    private DataRepository repository;
    private LiveData<List<EmplacementEntity>> mObservableEmplacements;

    public EmplacementViewModel(Application application) {
        super(application);
        repository = new DataRepository(application);
        mObservableEmplacements = repository.getAllEmplacements();
    }

    public LiveData<List<EmplacementEntity>> getAllEmplacements() {
        return mObservableEmplacements;
    }

    public LiveData<EmplacementEntity> getEmplacement(int id) {
        return repository.getEmplacement(id);
    }

    public void insertEmplacement(EmplacementEntity emplacementEntity) {
        repository.insertEmplacement(emplacementEntity);
    }
}
