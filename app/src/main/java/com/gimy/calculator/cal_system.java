package com.gimy.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import org.mariuszgromada.math.mxparser.Expression;


public class cal_system extends AppCompatActivity {

    EditText editText;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_system);


        editText = findViewById(R.id.screen_calc);
        editText.setShowSoftInputOnFocus(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.display).equals(editText.getText().toString()))
                {
                    editText.setText("");
                }
            }
        });

    }

    private void updateText(String strToAdd)
    {
        String oldstr = editText.getText().toString();
        int cursorPos = editText.getSelectionStart();
        String leftstr = oldstr.substring(0,cursorPos);
        String rightstr = oldstr.substring(cursorPos);
        if(getString(R.string.display).equals(editText.getText().toString()))
        {
            editText.setText(strToAdd);
            editText.setSelection(cursorPos+1);
        }
        else
        {
            editText.setText(String.format("%s%s%s" , leftstr , strToAdd , rightstr));
            editText.setSelection(cursorPos+1);
        }
    }

    public void Zero_fun(View view)
    {
        updateText("0");
    }

    public void one_fun(View view)
    {
        updateText("1");
    }

    public void two_fun(View view)
    {
        updateText("2");
    }

    public void three_fun(View view)
    {
        updateText("3");
    }

    public void four_fun(View view)
    {
        updateText("4");
    }

    public void five_fun(View view)
    {
        updateText("5");
    }

    public void six_fun(View view)
    {
        updateText("6");
    }

    public void seven_fun(View view)
    {
        updateText("7");
    }

    public void eigth_fun(View view)
    {
        updateText("8");
    }

    public void nine_fun(View view)
    {
        updateText("9");
    }

    public void clean_fun(View view)
    {
        editText.setText("");
    }

    public void exponent_fun(View view)
    {
        updateText("^");
    }

    public void parenthes_fun(View view)
    {
        int cursorpos = editText.getSelectionStart();
        int openpar = 0;
        int closepar = 0;
        int textLen = editText.getText().length();

        for(int i=0 ; i<cursorpos ;i++)
        {
            if(editText.getText().toString().substring(i , i+1).equals("("))
            {
                openpar = openpar +1;
            }
            if(editText.getText().toString().substring(i , i+1).equals(")"))
            {
                closepar = closepar +1;
            }
        }

        if(openpar == closepar || editText.getText().toString().substring(textLen-1 , textLen).equals("("))
        {
            updateText("(");
            editText.setSelection(cursorpos + 1);
        }
        else if(closepar < openpar &&  !editText.getText().toString().substring(textLen-1 , textLen).equals(")"))
        {
            updateText(")");
        }
        editText.setSelection(cursorpos + 1);

    }

    public void divide_fun(View view)
    {
        updateText("÷");
    }

    public void multiply_fun(View view)
    {
        updateText("x");
    }

    public void sum_fun(View view)
    {
        updateText("+");
    }

    public void subtract_fun(View view)
    {
        updateText("-");
    }

    public void point_fun(View view)
    {
        updateText(".");
    }

    public void equal_fun(View view)
    {
        String userExp = editText.getText().toString();

        userExp = userExp.replaceAll("÷" , "/");
        userExp = userExp.replaceAll("x" , "*");

        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());
        editText.setText(result);
        editText.setSelection(result.length());

    }

    public void backspace_fun(View view)
    {
        int cursorPos = editText.getSelectionStart();
        int Textlen = editText.getText().length();

        if(cursorPos != 0 && Textlen != 0)
        {
            SpannableStringBuilder selection = (SpannableStringBuilder) editText.getText();
            selection.replace(cursorPos-1 , cursorPos , "");
            editText.setText(selection);
            editText.setSelection(cursorPos-1);
        }

    }


}
