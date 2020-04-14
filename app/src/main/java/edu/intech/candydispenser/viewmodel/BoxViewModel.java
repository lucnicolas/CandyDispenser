package edu.intech.candydispenser.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.intech.candydispenser.data.box.Box;
import edu.intech.candydispenser.model.DataRepository;

/**
 * The type Emplacement view model.
 */
public class BoxViewModel extends AndroidViewModel {

    private final DataRepository repository;
    private final LiveData<List<Box>> mObservableEmplacements;

    /**
     * Instantiates a new Emplacement view model.
     *
     * @param application the application
     */
    public BoxViewModel(Application application) {
        super(application);
        repository = new DataRepository(application);
        mObservableEmplacements = repository.getAllEmplacements();
    }

    /**
     * Gets all emplacements.
     *
     * @return the all emplacements
     */
    public LiveData<List<Box>> getAllEmplacements() {
        return mObservableEmplacements;
    }

    /**
     * Gets emplacement.
     *
     * @param id the id
     * @return the emplacement
     */
    public LiveData<Box> getEmplacement(int id) {
        return repository.getEmplacement(id);
    }

    /**
     * Insert emplacement.
     *
     * @param box the emplacement entity
     */
    public void insertEmplacement(Box box) {
        repository.insertEmplacement(box);
    }

    /**
     * Update emplacement.
     *
     * @param box the emplacement entity
     */
    public void updateEmplacement(Box box) {
        repository.updateEmplacement(box);
    }
}
