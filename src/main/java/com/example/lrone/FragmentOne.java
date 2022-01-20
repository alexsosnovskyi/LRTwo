package com.example.lrone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class FragmentOne extends Fragment {

    RadioGroup price;
    RadioGroup firm;
    RadioButton radioButtonPrice;
    RadioButton radioButtonFirm;

    String[] dishes = {"Dishes", "plate", "spoon", "fork", "glass", "pan", "scoop"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_one, container, false);

        Spinner spinner = root.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, dishes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        price = root.findViewById(R.id.radioGroupPrice);
        firm = root.findViewById(R.id.radioGroupFirm);
        Button buttonApply = root.findViewById(R.id.button_apply);

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selected = spinner.getSelectedItem().toString();
                int id = spinner.getSelectedItemPosition();
                int radioIdPrice = price.getCheckedRadioButtonId();
                int radioIdFirm = firm.getCheckedRadioButtonId();

                if ((radioIdPrice == -1 || radioIdFirm == -1 || id == 0)) {
                    Toast.makeText(getContext(), "Please, enter all information",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    radioButtonPrice = root.findViewById(radioIdPrice);
                    radioButtonFirm = root.findViewById(radioIdFirm);

                    Bundle bundle = new Bundle();

                    bundle.putString("dishes", selected);
                    bundle.putString("price", radioButtonPrice.getText().toString());
                    bundle.putString("firm", radioButtonFirm.getText().toString());

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    FragmentTwo fragmentTwo = new FragmentTwo();
                    fragmentTwo.setArguments(bundle);

                    fragmentTransaction.replace(R.id.frame_container, fragmentTwo);
                    fragmentTransaction.commit();
                }
            }
        });

        return root;
    }
}