package edu.intech.candydispenser.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.List;

import edu.intech.candydispenser.R;
import edu.intech.candydispenser.viewmodel.ProductViewModel;
import edu.intech.candydispenser.db.entity.ProductEntity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static DecimalFormat df = new DecimalFormat("0.00");
    private ProductViewModel productViewModel;
    public static final int NEW_PRODUCT_ACTIVITY_REQUEST_CODE = 1;
    FloatingActionButton fab;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ProductListAdapter adapter = new ProductListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        productViewModel.getAllProducts().observe(this, new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(@Nullable final List<ProductEntity> productEntity) {
                // Update the cached copy of the products in the adapter.
                adapter.setProductEntities(productEntity);
            }
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        submit = findViewById(R.id.button);
        submit.setOnClickListener(this);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_PRODUCT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            try {
                ProductEntity productEntity = new ProductEntity(
                        data.getIntExtra(FormFragment.EXTRA_REPLY_NUMBER, -1),
                        data.getStringExtra(FormFragment.EXTRA_REPLY_NAME),
                        data.getFloatExtra(FormFragment.EXTRA_REPLY_PRICE, -1f), 0);
                productViewModel.insert(productEntity);
            } catch (NumberFormatException e) {
                Toast.makeText(
                        getApplicationContext(),
                        "Catch errors",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(fab)) {
            Intent intent = new Intent(MainActivity.this, NewProductActivity.class);
            startActivityForResult(intent, NEW_PRODUCT_ACTIVITY_REQUEST_CODE);
        }
        else if (v.equals(submit)) {
            EditText editText = findViewById(R.id.editText);
            final TextView message = findViewById(R.id.text);
            try {
                int number = Integer.parseInt(editText.getText().toString());
                productViewModel.getProduct(number).observe(this, new Observer<ProductEntity>() {
                    @Override
                    public void onChanged(ProductEntity productEntity) {
                        try {
                            String text = getString(R.string.display_data, productEntity.getName(), df.format(productEntity.getPrice()));
                            message.setText(text);
                        } catch (Exception e) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Product ID doesn't exist",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (Exception e) {
                Toast.makeText(
                        getApplicationContext(),
                        "Invalid product ID",
                        Toast.LENGTH_SHORT).show();
            }


        }
    }
}
