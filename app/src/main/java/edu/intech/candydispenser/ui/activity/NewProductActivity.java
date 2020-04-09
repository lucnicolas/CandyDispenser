package edu.intech.candydispenser.ui.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import edu.intech.candydispenser.R;
import edu.intech.candydispenser.ui.fragment.DispenserFragment;
import edu.intech.candydispenser.ui.fragment.FormFragment;

/**
 * The type New product activity.
 */
public class NewProductActivity extends AppCompatActivity {

    /**
     * The Btn one.
     */
    Button btnKeyOne;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        FragmentManager fragmentManager = getSupportFragmentManager();
        DispenserFragment dispenserFragment = new DispenserFragment();
        FormFragment formFragment = new FormFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentNewProduct, dispenserFragment, "FRAG_DISPENSER");
        transaction.commit();

    }

}