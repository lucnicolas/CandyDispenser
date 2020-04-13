package edu.intech.candydispenser.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.intech.candydispenser.db.entity.Emplacement;
import edu.intech.candydispenser.model.DataRepository;

/**
 * The type Emplacement view model.
 */
public class EmplacementViewModel extends AndroidViewModel {

    private final DataRepository repository;
    private final LiveData<List<Emplacement>> mObservableEmplacements;

    /**
     * Instantiates a new Emplacement view model.
     *
     * @param application the application
     */
    public EmplacementViewModel(Application application) {
        super(application);
        repository = new DataRepository(application);
        mObservableEmplacements = repository.getAllEmplacements();
    }

    /**
     * Gets all emplacements.
     *
     * @return the all emplacements
     */
    public LiveData<List<Emplacement>> getAllEmplacements() {
        return mObservableEmplacements;
    }

    /**
     * Gets emplacement.
     *
     * @param id the id
     * @return the emplacement
     */
    public LiveData<Emplacement> getEmplacement(int id) {
        return repository.getEmplacement(id);
    }

    /**
     * Insert emplacement.
     *
     * @param emplacement the emplacement entity
     */
    public void insertEmplacement(Emplacement emplacement) {
        repository.insertEmplacement(emplacement);
    }

    /**
     * Update emplacement.
     *
     * @param emplacement the emplacement entity
     */
    public void updateEmplacement(Emplacement emplacement) {
        repository.updateEmplacement(emplacement);
    }
}
