package edu.intech.candydispenser.ui.product;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import edu.intech.candydispenser.R;
import edu.intech.candydispenser.data.product.Product;
import edu.intech.candydispenser.viewmodel.BoxViewModel;
import edu.intech.candydispenser.viewmodel.ProductViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    /**
     * The constant EXTRA_REPLY_NAME.
     */
    public static final String EXTRA_REPLY_NAME = "edu.intech.candydispenser.name.REPLY";
    /**
     * The constant EXTRA_REPLY_NUMBER.
     */
    public static final String EXTRA_REPLY_NUMBER = "edu.intech.candydispenser.number.REPLY";
    /**
     * The constant EXTRA_REPLY_PRICE.
     */
    public static final String EXTRA_REPLY_PRICE = "edu.intech.candydispenser.price.REPLY";

    /**
     * The constant EXTRA_REPLY_TYPE.
     */
    public static final String EXTRA_REPLY_TYPE = "edu.intech.candydispenser.type.REPLY";


    private EditText mEditNameView;
    private EditText mEditNumberView;
    private EditText mEditPriceView;

    private Button mRemoveButton;

    private FragmentManager fragmentManager;
    private DispenserFragment dispenserFragment;

    private ProductViewModel productViewModel;
    private BoxViewModel boxViewModel;


    /**
     * Instantiates a new Form fragment.
     */
    public FormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormFragment.
     */
    // TODO: Rename and change types and number of parameters
    private static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_form, container, false);

        mRemoveButton = inflatedView.findViewById(R.id.button_remove);
        mRemoveButton.setVisibility(View.GONE);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        boxViewModel = new ViewModelProvider(this).get(BoxViewModel.class);

        fragmentManager = getActivity().getSupportFragmentManager();
        dispenserFragment = new DispenserFragment();

        mEditNameView = inflatedView.findViewById(R.id.edit_name);
        mEditNumberView = inflatedView.findViewById(R.id.edit_number);

        // OnClick on the EditText Number, call dispenser fragment
        mEditNumberView.setFocusable(false);
        mEditNumberView.setFocusableInTouchMode(false);
        mEditNumberView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        getActivity(),
                        "You can't edit the box ID, go back or suppress this product",
                        Toast.LENGTH_SHORT).show();
            }
        });
        mEditPriceView = inflatedView.findViewById(R.id.edit_price);

        final Bundle bundle = this.getArguments();

        if (bundle != null) {
            if (bundle.getBoolean("UPDATE")) {
                productViewModel.getProduct(bundle.getInt("KEY")).observe(getViewLifecycleOwner(), new Observer<Product>() {
                    @Override
                    public void onChanged(Product product) {
                        mEditNameView.setText(product.getName());
                        mEditPriceView.setText(String.valueOf(product.getPrice()));
                    }
                });
                // Removing action
                mRemoveButton.setVisibility(View.VISIBLE);
                mRemoveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        productViewModel.getProduct(bundle.getInt("KEY")).observe(getViewLifecycleOwner(), new Observer<Product>() {
                            @Override
                            public void onChanged(Product product) {
                                productViewModel.removeProduct(product);
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.replace(R.id.fragmentNewProduct, dispenserFragment, "FRAG_DISPENSER");
                                transaction.commit();
                            }
                        });
                    }
                });
            }
            int key = bundle.getInt("KEY", -1);
            String strKey = String.valueOf(key);
            mEditNumberView.setText(strKey);
        }


        final Button button = inflatedView.findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditNumberView.getText())
                        || TextUtils.isEmpty(mEditNameView.getText())
                        || TextUtils.isEmpty(mEditPriceView.getText())) {
                    getActivity().setResult(Activity.RESULT_CANCELED, replyIntent);
                } else {
                    if (bundle.getBoolean("UPDATE")) {
                        replyIntent.putExtra(EXTRA_REPLY_TYPE, "UPDATE");
                    }
                    else {
                        replyIntent.putExtra(EXTRA_REPLY_TYPE, "INSERT");
                    }
                    String name = mEditNameView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY_NAME, name);
                    String number = mEditNumberView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY_NUMBER, Integer.parseInt(number));
                    String price = mEditPriceView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY_PRICE, Float.parseFloat(price));
                    getActivity().setResult(Activity.RESULT_OK, replyIntent);
                }
                getActivity().finish();
            }
        });
        // Inflate the layout for this fragment
        return inflatedView;
    }
}
