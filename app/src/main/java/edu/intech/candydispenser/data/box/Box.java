package edu.intech.candydispenser.data.box;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The type Emplacement entity.
 */
@Entity(tableName = "Box")
public class Box {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @NonNull @ColumnInfo(name = "productId", defaultValue = "0") private int productId;

    /**
     * Instantiates a new Emplacement entity.
     *
     * @param id the id
     */
    public Box(int id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
}
