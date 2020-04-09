package edu.intech.candydispenser.ui;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import edu.intech.candydispenser.R;

public class NewProductActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private DispenserFragment dispenserFragment;
    private FormFragment formFragment;

    Button btnKeyOne;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        fragmentManager = getSupportFragmentManager();
        dispenserFragment = new DispenserFragment();
        formFragment = new FormFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentNewProduct, dispenserFragment, "FRAG_DISPENSER");
        transaction.commit();

    }

}