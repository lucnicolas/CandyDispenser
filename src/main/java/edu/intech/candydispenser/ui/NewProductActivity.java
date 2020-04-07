package edu.intech.candydispenser.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.intech.candydispenser.R;

public class NewProductActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_NAME = "edu.intech.candydispenser.name.REPLY";
    public static final String EXTRA_REPLY_NUMBER = "edu.intech.candydispenser.number.REPLY";
    public static final String EXTRA_REPLY_PRICE = "edu.intech.candydispenser.price.REPLY";


    private EditText mEditNameView;
    private EditText mEditNumberView;
    private EditText mEditPriceView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        mEditNameView = findViewById(R.id.edit_name);
        mEditNumberView = findViewById(R.id.edit_number);
        mEditPriceView = findViewById(R.id.edit_price);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditNumberView.getText())
                        || TextUtils.isEmpty(mEditNameView.getText())
                        || TextUtils.isEmpty(mEditPriceView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String name = mEditNameView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY_NAME, name);
                    String number = mEditNumberView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY_NUMBER, Integer.parseInt(number));
                    String price = mEditPriceView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY_PRICE, Float.parseFloat(price));
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}