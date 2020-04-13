package edu.intech.candydispenser.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type App database.
 */
@Database(entities = {Product.class, Box.class}, version = 7)
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
                    BoxDao boxDao = INSTANCE.emplacementDao();

                    boxDao.deleteAllEmplacement();
                    productDao.deleteAllProducts();

                    for (int i = 1; i <= 18; i++) {
                        Box box = new Box(i);
                        boxDao.insertEmplacement(box);
                    }
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
    public abstract BoxDao emplacementDao();
}
