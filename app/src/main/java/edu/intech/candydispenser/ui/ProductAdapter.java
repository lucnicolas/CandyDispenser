package edu.intech.candydispenser.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.intech.candydispenser.R;
import edu.intech.candydispenser.data.product.Product;

/**
 * The type Product list adapter.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final LayoutInflater mInflater;
    private List<Product> productEntities; // Cached copy of words

    /**
     * Instantiates a new Product list adapter.
     *
     * @param context the context
     */
    public ProductAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Set product entities.
     *
     * @param productEntities the product entities
     */
    public void setProductEntities(List<Product> productEntities) {
        this.productEntities = productEntities;
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (productEntities != null) {
            Product current = productEntities.get(position);
            holder.productItemView.setText(current.getNumber() + " - " + current.getName() + " - " + current.getPrice());
        } else {
            // Covers the case of data not being ready yet.
            holder.productItemView.setText(R.string.no_product);
        }
    }

    // getItemCount() is called many times, and when it is first called,
    // productEntities has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (productEntities != null)
            return productEntities.size();
        else return 0;
    }

    /**
     * The type Product view holder.
     */
    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView productItemView;

        private ProductViewHolder(View itemView) {
            super(itemView);
            productItemView = itemView.findViewById(R.id.textView);
        }
    }
}

