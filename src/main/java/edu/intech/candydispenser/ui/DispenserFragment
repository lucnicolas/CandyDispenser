package edu.intech.candydispenser.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.List;

import edu.intech.candydispenser.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DispenserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DispenserFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Bundle arguments;
    private FragmentManager fragmentManager;
    private FormFragment formFragment;

    public DispenserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DispenserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DispenserFragment newInstance(String param1, String param2) {
        DispenserFragment fragment = new DispenserFragment();
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
        View inflatedView = inflater.inflate(R.layout.fragment_dispenser, container, false);;

        fragmentManager = getActivity().getSupportFragmentManager();
        formFragment = new FormFragment();
        arguments = new Bundle();

        GridLayout gridLayout = inflatedView.findViewById(R.id.girdLayoutDispenser);
        // create buttons in a loop
        for (int i = 1; i <= 18; i++) {
            Button button = new Button(getActivity());
            button.setText(String.valueOf(i));
            // R.id won't be generated for us, so we need to create one
            button.setId(i);

            // add our event handler (less memory than an anonymous inner class)
            button.setOnClickListener(this);

            // add generated button to view
            gridLayout.addView(button);
        }

        // Inflate the layout for this fragment
        return inflatedView;
    }

    @Override
    public void onClick(View v) {

        // TODO: Check if clicked button is available, if not update him

        arguments.putInt("KEY", v.getId());
        formFragment.setArguments(arguments);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentNewProduct, formFragment, "FRAG_FORM");

        transaction.commit();


    }
}
