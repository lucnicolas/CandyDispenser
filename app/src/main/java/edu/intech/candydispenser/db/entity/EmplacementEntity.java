package edu.intech.candydispenser.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Emplacements")
public class EmplacementEntity {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") private int id;

    @NonNull @ColumnInfo(name = "productId", defaultValue = "0") private int productId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
