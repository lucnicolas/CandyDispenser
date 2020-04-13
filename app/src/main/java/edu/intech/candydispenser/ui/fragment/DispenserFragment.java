package edu.intech.candydispenser.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.intech.candydispenser.R;
import edu.intech.candydispenser.db.entity.Emplacement;
import edu.intech.candydispenser.ui.adapter.EmplacementAdapter;
import edu.intech.candydispenser.viewmodel.EmplacementViewModel;

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
    private EmplacementAdapter adapter;

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

        final List<Emplacement> emplacementList = null;

        RecyclerView recyclerView = inflatedView.findViewById(R.id.recyclerview_buttons);
        adapter = new EmplacementAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns)); // Initialized RecyclerView using GirdLayoutManager

        EmplacementViewModel emplacementViewModel = new ViewModelProvider(this).get(EmplacementViewModel.class);
        emplacementViewModel.getAllEmplacements().observe(getViewLifecycleOwner(), new Observer<List<Emplacement>>() {
            @Override
            public void onChanged(List<Emplacement> emplacements) {
                adapter.setEmplacements(emplacements);
            }
        });

        // Inflate the layout for this fragment
        return inflatedView;
    }
}
