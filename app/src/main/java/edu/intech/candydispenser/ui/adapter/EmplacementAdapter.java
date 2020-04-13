package edu.intech.candydispenser.ui.adapter;

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
import edu.intech.candydispenser.db.entity.EmplacementEntity;
import edu.intech.candydispenser.ui.activity.NewProductActivity;
import edu.intech.candydispenser.ui.fragment.FormFragment;

/**
 * The type Emplacement adapter.
 */
public class EmplacementAdapter extends RecyclerView.Adapter<EmplacementAdapter.EmplacementViewHolder> {

    private final LayoutInflater mInflater;
    private Bundle arguments;
    private FragmentManager fragmentManager;
    private FormFragment formFragment;
    private List<EmplacementEntity> emplacements;

    /**
     * Instantiates a new Emplacement adapter.
     *
     * @param context the context
     */
    public EmplacementAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Sets emplacements.
     *
     * @param emplacements the emplacements
     */
    public void setEmplacements(List<EmplacementEntity> emplacements) {
        this.emplacements = emplacements;
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
        if (emplacements != null) {
            EmplacementEntity current = emplacements.get(position);
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
        if (emplacements != null)
            return emplacements.size();
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
