package ca.nscc.blackjackapp.ui.credits;
import ca.nscc.blackjackapp.R;
import ca.nscc.blackjackapp.databinding.FragmentCreditBinding;
import ca.nscc.blackjackapp.databinding.FragmentGameBinding;
import ca.nscc.blackjackapp.ui.game.GameViewModel;

import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CreditFragment extends Fragment {

    private CreditViewModel mViewModel;
    private FragmentCreditBinding binding;
    private SharedPreferences pref;


    public static CreditFragment newInstance() {
        return new CreditFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CreditViewModel.class);

        binding = FragmentCreditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();





        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CreditViewModel.class);
        // TODO: Use the ViewModel
    }



}