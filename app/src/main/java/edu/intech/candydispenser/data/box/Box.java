package edu.intech.candydispenser.data.box;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The type Emplacement entity.
 */
@Entity(tableName = "Box")
public class Box {

    @PrimaryKey @ColumnInfo(name = "id") private int id;

    @ColumnInfo(name = "product_name") private String productName;

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
    public String getProductName() {
        return productName;
    }

    /**
     * Sets product id.
     *
     * @param productName the product id
     */
    public void setProductName(String  productName) {
        this.productName = productName;
    }
}
