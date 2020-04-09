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
import edu.intech.candydispenser.db.entity.EmplacementEntity;
import edu.intech.candydispenser.db.entity.ProductEntity;

@Database(entities = {ProductEntity.class, EmplacementEntity.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract ProductDao productDao();

    public abstract EmplacementDao emplacementDao();

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

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
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
                        EmplacementEntity emplacementEntity = new EmplacementEntity();
                        emplacementDao.insertEmplacement(emplacementEntity);
                    }

                    ProductEntity productEntity = new ProductEntity(1, "Cookies", 0.80f, 0);
                    productDao.insertProduct(productEntity);
                    productEntity = new ProductEntity(2, "Water", 1.00f, 0);
                    productDao.insertProduct(productEntity);
                    productEntity = new ProductEntity(3, "KitKat", 0.80f, 0);
                    productDao.insertProduct(productEntity);
                }
            });
        }
    };
}
