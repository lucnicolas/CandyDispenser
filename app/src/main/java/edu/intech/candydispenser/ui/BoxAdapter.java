package edu.intech.candydispenser.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.intech.candydispenser.R;
import edu.intech.candydispenser.data.box.Box;
import edu.intech.candydispenser.ui.product.FormFragment;
import edu.intech.candydispenser.ui.product.NewProductActivity;

/**
 * The type Emplacement adapter.
 */
public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.EmplacementViewHolder> {

    private final LayoutInflater mInflater;
    private Bundle arguments;
    private FragmentManager fragmentManager;
    private FormFragment formFragment;
    private List<Box> boxes;

    /**
     * Instantiates a new Emplacement adapter.
     *
     * @param context the context
     */
    public BoxAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Sets emplacements.
     *
     * @param boxes the emplacements
     */
    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmplacementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_button, parent, false);
        formFragment = new FormFragment();
        arguments = new Bundle();
        return new EmplacementViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmplacementViewHolder holder, int position) {
        if (boxes != null) {
            Box current = boxes.get(position);
            Log.d("DEBUG", "Emplacement numéro : " + current.getId());
            Button button = holder.emplacementItemView;
            button.setText(String.valueOf(current.getId()));
            // R.id won't be generated for us, so we need to create one
            button.setId(current.getId());
            if (current.getProductId() != 0) {
                button.setBackgroundTintList(ColorStateList.valueOf(Color.DKGRAY));
                button.setTextColor(ColorStateList.valueOf(Color.WHITE));
            }

            // add our event handler (less memory than an anonymous inner class)
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragmentManager = ((NewProductActivity) v.getContext()).getSupportFragmentManager(); // instantiate view context
                    Log.d("DEBUG", "ID = " + v.getId());
                    arguments.putInt("KEY", v.getId());
                    formFragment.setArguments(arguments);
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragmentNewProduct, formFragment, "FRAG_FORM");
                    transaction.commit();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (boxes != null)
            return boxes.size();
        else return 0;
    }

    /**
     * The type Emplacement view holder.
     */
    static class EmplacementViewHolder extends RecyclerView.ViewHolder {
        private final Button emplacementItemView;

        private EmplacementViewHolder(View itemView) {
            super(itemView);
            emplacementItemView = itemView.findViewById(R.id.button_recyclerview);
        }
    }
}
