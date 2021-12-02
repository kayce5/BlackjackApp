package ca.nscc.blackjackapp.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ca.nscc.blackjackapp.R;
import ca.nscc.blackjackapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private EditText name;
    private TextView message;
    private Button button1;

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    SharedPreferences pref;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Link Variables
        name = (EditText) root.findViewById(R.id.txtName);
        button1 = (Button) root.findViewById(R.id.btnEnter);
        message = (TextView) root.findViewById(R.id.message);
        pref = requireActivity().getSharedPreferences("fragment_game", Context.MODE_PRIVATE);

        //Button Call
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playerName = name.getText().toString();
                if(playerName.isEmpty()){
                    Toast.makeText(getContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                } else {
                    message.setVisibility(View.VISIBLE);
                }

                //Shared Pref
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("playerName", playerName);
                editor.commit();





            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}