package com.example.navicalculator.ui.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.navicalculator.R;
import com.example.navicalculator.databinding.FragmentBaseBinding;

public class BaseFragment extends Fragment {

    private FragmentBaseBinding binding;
    private Spinner fromSpinner, toSpinner;
    private EditText enterNumb, result;
    private Button convertButton;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBaseBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        enterNumb = root.findViewById(R.id.numberEntered);
        enterNumb.setShowSoftInputOnFocus(false);
        enterNumb.setText("");

        result = root.findViewById(R.id.result);
        result.setShowSoftInputOnFocus(false);
        result.setText("");

        fromSpinner = root.findViewById(R.id.fromSpinner);
        toSpinner = root.findViewById(R.id.toSpinner);
        String[] bases = {"2", "8", "10", "16"};
        ArrayAdapter ad = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.spinner_item, bases);
        fromSpinner.setAdapter(ad);
        toSpinner.setAdapter(ad);

        convertButton = root.findViewById(R.id.convert_button);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertNumber(fromSpinner.getSelectedItem().toString(), toSpinner.getSelectedItem().toString());
            }
        });

        return root;
    }

    void convertNumber(String fSpinner, String tSpinner) {
        String numberEntered = enterNumb.getText().toString();
        int fBase = Integer.parseInt(fSpinner);
        int tBase = Integer.parseInt(tSpinner);

        if (numberEntered.equals(""))
            return;

        int decimalValue;
        String resStr = "";

        try {
            decimalValue = Integer.parseInt(numberEntered, fBase);
        } catch(Exception e) {
            result.setTextColor(Color.RED);
            result.setText("Wrong input type!");
            return;
        }


        switch (tBase) {
            case 2:
                resStr = Integer.toBinaryString(decimalValue);
                break;

            case 8:
                resStr = Integer.toOctalString(decimalValue);
                break;

            case 10:
                resStr = Integer.toString(decimalValue);
                break;

            case 16:
                resStr = Integer.toHexString(decimalValue).toUpperCase();
                break;
        }

        result.setTextColor(Color.BLACK);
        result.setText(resStr);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}