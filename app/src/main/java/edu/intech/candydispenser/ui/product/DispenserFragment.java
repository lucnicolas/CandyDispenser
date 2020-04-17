package edu.intech.candydispenser.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.intech.candydispenser.R;
import edu.intech.candydispenser.data.box.Box;
import edu.intech.candydispenser.ui.BoxAdapter;
import edu.intech.candydispenser.viewmodel.BoxViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DispenserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DispenserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Bundle arguments;
    private FragmentManager fragmentManager;
    private FormFragment formFragment;
    private BoxAdapter adapter;

    /**
     * Instantiates a new Dispenser fragment.
     */
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
    private static DispenserFragment newInstance(String param1, String param2) {
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
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflatedView = inflater.inflate(R.layout.fragment_dispenser, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();
        formFragment = new FormFragment();
        arguments = new Bundle();

        final List<Box> boxList = null;

        final RecyclerView recyclerView = inflatedView.findViewById(R.id.recyclerview_buttons);
        adapter = new BoxAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns)); // Initialized RecyclerView using GirdLayoutManager

        final BoxViewModel boxViewModel = new ViewModelProvider(this).get(BoxViewModel.class);
        boxViewModel.getAllBoxes().observe(getViewLifecycleOwner(), new Observer<List<Box>>() {
            @Override
            public void onChanged(List<Box> boxes) {
                adapter.setBoxes(boxes);
            }
        });

        final Button newBox = inflatedView.findViewById(R.id.newBox);
        newBox.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Box currentBox = new Box(recyclerView.getChildCount() + 1);
                boxViewModel.insertBox(currentBox);
            }
        });

        Button deleteLastBox = inflatedView.findViewById(R.id.deleteBox);
        deleteLastBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boxViewModel.deleteLastBox(recyclerView.getChildCount());
            }
        });

        // Inflate the layout for this fragment
        return inflatedView;
    }
}
