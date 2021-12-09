package com.example.navicalculator.ui.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import com.example.navicalculator.R;
import com.example.navicalculator.databinding.FragmentCalculatorBinding;

public class CalculatorFragment extends Fragment {

    private FragmentCalculatorBinding binding;
    private EditText display1, display2;
    private Button zeroButton, oneButton, twoButton, threeButton, fourButton,
                   fiveButton, sixButton, sevenButton, eightButton, nineButton,
                   cButton, ceButton, plusButton, subtractButton, multiplyButton, divideButton,
                   changeSignButton, equalButton;
    private AppCompatImageButton backspaceButton;
    boolean operatorButtonCLicked = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCalculatorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        display1 = root.findViewById(R.id.editText1);
        display1.setShowSoftInputOnFocus(false);
        display1.setText("");

        display2 = root.findViewById(R.id.editText2);
        display2.setShowSoftInputOnFocus(false);
        display2.setText("0");

        initButtons(root, display1, display2);

        return root;
    }

    private void initButtons(View root, EditText display1, EditText display2) {
        zeroButton = root.findViewById(R.id.zero_button);
        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText("0");
            }
        });

        oneButton = root.findViewById(R.id.one_button);
        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText("1");
            }
        });

        twoButton = root.findViewById(R.id.two_button);
        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText("2");
            }
        });

        threeButton = root.findViewById(R.id.three_button);
        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText("3");
            }
        });

        fourButton = root.findViewById(R.id.four_button);
        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText("4");
            }
        });

        fiveButton = root.findViewById(R.id.five_button);
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText("5");
            }
        });

        sixButton = root.findViewById(R.id.six_button);
        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText("6");
            }
        });

        sevenButton = root.findViewById(R.id.seven_button);
        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText("7");
            }
        });

        eightButton = root.findViewById(R.id.eight_button);
        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText("8");
            }
        });

        nineButton = root.findViewById(R.id.nine_button);
        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText("9");
            }
        });

        cButton = root.findViewById(R.id.c_button);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display1.setText("");
                display2.setText("0");
            }
        });

        ceButton = root.findViewById(R.id.ce_button);
        ceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display2.setText("0");
            }
        });

        plusButton = root.findViewById(R.id.plus_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOperator("+");
            }
        });

        subtractButton = root.findViewById(R.id.subtract_button);
        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOperator("-");
            }
        });

        multiplyButton = root.findViewById(R.id.multiply_button);
        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOperator("×");
            }
        });

        divideButton = root.findViewById(R.id.divide_button);
        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOperator("÷");
            }
        });

        backspaceButton = root.findViewById(R.id.backspace_button);
        backspaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldStr = display2.getText().toString();
                int len = oldStr.length();
                if (len > 0)
                    display2.setText(oldStr.substring(0, len - 1));
            }
        });

        changeSignButton = root.findViewById(R.id.change_sign_button);
        changeSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldStr = display2.getText().toString();
                int len = oldStr.length();
                if (len > 0) {
                    if (oldStr.charAt(0) == '-')
                        display2.setText(oldStr.substring(1, len));
                    else
                        display2.setText(String.format("%s%s", "-", oldStr));
                }
            }
        });

        equalButton = root.findViewById(R.id.equal_button);
        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                display1.setText("");
                operatorButtonCLicked = true;
            }
        });
    }

    private void calculate() {
        String str1 = display1.getText().toString();
        String str2 = display2.getText().toString();

        int len1 = str1.length();
        if (len1 != 0) {
            int operand1 = Integer.parseInt(str1.substring(0, len1 - 1));
            int operand2 = Integer.parseInt(str2);
            char operator = str1.charAt(len1 - 1);

            int result = 0;
            switch (operator) {
                case '+':
                    result = operand1 + operand2;
                    break;
                case '-':
                    result = operand1 - operand2;
                    break;
                case '×':
                    result = operand1 * operand2;
                    break;
                case '÷':
                    if (operand2 != 0)
                        result = operand1 / operand2;
                    break;
            }

            display2.setText(String.valueOf(result));
        }
    }

    private void updateText(String newStr) {
        if (operatorButtonCLicked) {
            display2.setText("");
        }

        String oldStr = display2.getText().toString();
        if (oldStr.equals("0")) {
            display2.setText("");
            display2.setText(newStr);
        } else {
            display2.setText(String.format("%s%s", oldStr, newStr));
        }

        operatorButtonCLicked = false;
    }

    private void updateOperator(String newStr) {
        String oldStr1 = display1.getText().toString();
        String oldStr2 = display2.getText().toString();
        int lenStr1 = oldStr1.length();

        if (lenStr1 == 0) {
            display1.setText(String.format("%s%s", oldStr2, newStr));
        } else if (!operatorButtonCLicked) {
            calculate();
            oldStr2 = display2.getText().toString();
            display1.setText(String.format("%s%s", oldStr2, newStr));
        } else {
            display1.setText(String.format("%s%s", oldStr1.substring(0, lenStr1 - 1), newStr));
        }

        operatorButtonCLicked = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}