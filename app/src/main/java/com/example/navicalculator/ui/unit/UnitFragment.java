package com.example.navicalculator.ui.unit;

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
import com.example.navicalculator.databinding.FragmentUnitBinding;

import java.util.HashMap;

public class UnitFragment extends Fragment {

    private FragmentUnitBinding binding;
    private Spinner fromSpinner, toSpinner;
    private EditText enterNumb, result;
    private Button convertButton;
    private View root;
    private HashMap<String, Double> toMeters = new HashMap<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUnitBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        enterNumb = root.findViewById(R.id.numberEntered1);
        enterNumb.setShowSoftInputOnFocus(false);
        enterNumb.setText("");

        result = root.findViewById(R.id.result1);
        result.setShowSoftInputOnFocus(false);
        result.setText("");

        fromSpinner = root.findViewById(R.id.fromSpinner1);
        toSpinner = root.findViewById(R.id.toSpinner1);
        String[] bases = {"in", "cm", "ft", "yd", "m", "mi", "km"};
        ArrayAdapter ad = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.spinner_item1, bases);
        fromSpinner.setAdapter(ad);
        toSpinner.setAdapter(ad);

        toMeters.put("cm", 0.01);
        toMeters.put("m", 1.0);
        toMeters.put("km", 1000.0);
        toMeters.put("in", 0.0254);
        toMeters.put("ft", 0.3048);
        toMeters.put("yd", 0.9144);
        toMeters.put("mi", 1609.34);

        convertButton = root.findViewById(R.id.convert_button1);
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

        if (numberEntered.equals(""))
            return;

        double res;
        double inputNum = Double.valueOf(numberEntered);

        try {
            res = inputNum * toMeters.get(fSpinner) * 1/(toMeters.get(tSpinner));
        } catch(Exception e) {
            result.setTextColor(Color.RED);
            result.setText("Wrong input type!");
            return;
        }

        result.setTextColor(Color.BLACK);
        result.setText(res + "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}