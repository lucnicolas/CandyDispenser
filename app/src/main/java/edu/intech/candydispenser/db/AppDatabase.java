package edu.intech.candydispenser.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.intech.candydispenser.db.dao.EmplacementDao;
import edu.intech.candydispenser.db.dao.ProductDao;
import edu.intech.candydispenser.db.entity.Emplacement;
import edu.intech.candydispenser.db.entity.Product;

/**
 * The type App database.
 */
@Database(entities = {Product.class, Emplacement.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    /**
     * The constant databaseWriteExecutor.
     */
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    // Populate the database in the background.
                    // If you want to start with more words, just add them.
                    ProductDao productDao = INSTANCE.productDao();
                    EmplacementDao emplacementDao = INSTANCE.emplacementDao();

                    emplacementDao.deleteAllEmplacement();
                    productDao.deleteAllProducts();

                    for (int i = 1; i <= 18; i++) {
                        Emplacement emplacement = new Emplacement(i);
                        emplacementDao.insertEmplacement(emplacement);
                    }

                    Product product = new Product(1, "Cookies", 0.80f);
                    productDao.insertProduct(product);
                    product = new Product(2, "Water", 1.00f);
                    productDao.insertProduct(product);
                    product = new Product(3, "KitKat", 0.80f);
                    productDao.insertProduct(product);
                }
            });
        }
    };

    /**
     * Gets database.
     *
     * @param context the context
     * @return the database
     */
    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    "CandyDispenser.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback).build();
            //.addCallback(sRoomDatabaseCallback).build();
            //.allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    /**
     * Product dao product dao.
     *
     * @return the product dao
     */
    public abstract ProductDao productDao();

    /**
     * Emplacement dao emplacement dao.
     *
     * @return the emplacement dao
     */
    public abstract EmplacementDao emplacementDao();
}
