package edu.intech.candydispenser.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import edu.intech.candydispenser.R;

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

    public static final String EXTRA_REPLY_NAME = "edu.intech.candydispenser.name.REPLY";
    public static final String EXTRA_REPLY_NUMBER = "edu.intech.candydispenser.number.REPLY";
    public static final String EXTRA_REPLY_PRICE = "edu.intech.candydispenser.price.REPLY";

    private EditText mEditNameView;
    private EditText mEditNumberView;
    private EditText mEditPriceView;

    private FragmentManager fragmentManager;
    private DispenserFragment dispenserFragment;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
    public static FormFragment newInstance(String param1, String param2) {
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_form, container, false);

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
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentNewProduct, dispenserFragment, "FRAG_DISPENSER");
                transaction.commit();
            }
        });
        mEditPriceView = inflatedView.findViewById(R.id.edit_price);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
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
