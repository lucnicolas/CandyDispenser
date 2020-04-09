package edu.intech.candydispenser.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.intech.candydispenser.R;
import edu.intech.candydispenser.db.entity.ProductEntity;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private final LayoutInflater mInflater;
    private List<ProductEntity> productEntities; // Cached copy of words

    public ProductListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (productEntities != null) {
            ProductEntity current = productEntities.get(position);
            holder.productItemView.setText(current.getNumber() + " - " + current.getName() + " - " + current.getPrice());
        } else {
            // Covers the case of data not being ready yet.
            holder.productItemView.setText(R.string.no_product);
        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ProductViewHolder(itemView);
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView productItemView;

        private ProductViewHolder(View itemView) {
            super(itemView);
            productItemView = itemView.findViewById(R.id.textView);
        }
    }

    public void setProductEntities(List<ProductEntity> productEntities){
        this.productEntities = productEntities;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (productEntities != null)
            return productEntities.size();
        else return 0;
    }
}
