package edu.intech.candydispenser.ui.main;

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
import edu.intech.candydispenser.data.box.Box;
import edu.intech.candydispenser.data.product.Product;
import edu.intech.candydispenser.ui.ProductAdapter;
import edu.intech.candydispenser.ui.product.FormFragment;
import edu.intech.candydispenser.ui.product.NewProductActivity;
import edu.intech.candydispenser.viewmodel.BoxViewModel;
import edu.intech.candydispenser.viewmodel.ProductViewModel;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private ProductViewModel productViewModel;
    private BoxViewModel boxViewModel;
    private static final int NEW_PRODUCT_ACTIVITY_REQUEST_CODE = 1;
    private FloatingActionButton fab;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ProductAdapter adapter = new ProductAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        boxViewModel = new ViewModelProvider(this).get(BoxViewModel.class);

        productViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable final List<Product> product) {
                // Update the cached copy of the products in the adapter.
                adapter.setProductEntities(product);
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
            final int boxId = data.getIntExtra(FormFragment.EXTRA_REPLY_NUMBER, -1);
            final String productName = data.getStringExtra(FormFragment.EXTRA_REPLY_NAME);
            final float productPrice = data.getFloatExtra(FormFragment.EXTRA_REPLY_PRICE, -1f);
            if (data.getStringExtra(FormFragment.EXTRA_REPLY_TYPE).equals("INSERT")) {
                final Product product = new Product(
                        boxId,
                        productName,
                        productPrice);
                productViewModel.insertProduct(product);

                boxViewModel.getBox(product.getBoxId()).observe(this, new Observer<Box>() {
                    @Override
                    public void onChanged(Box box) {
                        box.setProductName(product.getName());
                        boxViewModel.updateBox(box);
                    }
                });
            }
            else if (data.getStringExtra(FormFragment.EXTRA_REPLY_TYPE).equals("UPDATE")) {
                productViewModel.getProduct(boxId).observe(this, new Observer<Product>() {
                    @Override
                    public void onChanged(Product product) {
                        product.setBoxId(boxId);
                        product.setName(productName);
                        product.setPrice(productPrice);
                        productViewModel.updateProduct(product);
                    }
                });
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
        } else if (v.equals(submit)) {
            EditText editText = findViewById(R.id.editText);
            final TextView message = findViewById(R.id.text);
            try {
                int number = Integer.parseInt(editText.getText().toString());
                productViewModel.getProduct(number).observe(this, new Observer<Product>() {
                    @Override
                    public void onChanged(Product product) {
                        try {
                            String text = getString(R.string.display_data, product.getName(), df.format(product.getPrice()));
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
