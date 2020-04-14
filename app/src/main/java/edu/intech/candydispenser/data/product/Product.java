package edu.intech.candydispenser.data.product;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * The type Product entity.
 */
@Entity(tableName = "Product", indices = {@Index(value = {"box_id"}, unique = true)})
public class Product {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") private int id;

    @NonNull @ColumnInfo(name = "box_id") private int boxId;

    @NonNull @ColumnInfo(name = "name") private String name;

    @NonNull @ColumnInfo(name = "price") private float price;

    /**
     * Instantiates a new Product entity.
     *
     * @param boxId the number
     * @param name  the name
     * @param price the price
     */
    public Product(int boxId, String name, Float price) {
        this.boxId = boxId;
        this.name = name;
        this.price = price;
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
     * Gets number.
     *
     * @return the number
     */
    public int getBoxId() {
        return boxId;
    }

    /**
     * Sets number.
     *
     * @param boxId the number
     */
    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(float price) {
        this.price = price;
    }
}
