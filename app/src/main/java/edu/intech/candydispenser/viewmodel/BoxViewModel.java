package edu.intech.candydispenser.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.intech.candydispenser.data.DataRepository;
import edu.intech.candydispenser.data.box.Box;

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
        mObservableEmplacements = repository.getAllBoxes();
    }

    /**
     * Gets all emplacements.
     *
     * @return the all emplacements
     */
    public LiveData<List<Box>> getAllBoxes() {
        return mObservableEmplacements;
    }

    /**
     * Gets emplacement.
     *
     * @param id the id
     * @return the emplacement
     */
    public LiveData<Box> getBox(int id) {
        return repository.getBox(id);
    }

    /**
     * Insert emplacement.
     *
     * @param box the emplacement entity
     */
    public void insertBox(Box box) {
        repository.insertBox(box);
    }

    /**
     * Update emplacement.
     *
     * @param box the emplacement entity
     */
    public void updateBox(Box box) {
        repository.updateBox(box);
    }

    /**
     * Remove box.
     *
     * @param box the box
     */
    public void removeBox(Box box) { repository.removeBox(box); }

    public LiveData<Box> getLastBox() { return repository.getLastBox(); }
}
