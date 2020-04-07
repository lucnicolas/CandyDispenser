package edu.intech.candydispenser.data.models;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1, entities = {Product.class})
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDAO productDao();

    private static volatile ProductDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ProductDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ProductDatabase.class,
                    "CandyDispenser.db")
                    .addCallback(sRoomDatabaseCallback).build(); //.allowMainThreadQueries()
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
                    ProductDAO dao = INSTANCE.productDao();
                    dao.deleteAll();

                    Product product = new Product(1, "Cookies", 0.80f);
                    dao.insert(product);
                    product = new Product(2, "Water", 1.00f);
                    dao.insert(product);
                    product = new Product(3, "KitKat", 0.80f);
                    dao.insert(product);
                }
            });
        }
    };
}
