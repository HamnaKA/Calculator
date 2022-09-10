package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    TextView result, solution;
    MaterialButton buttonac, buttonbracketopen, buttonbracketclose;
    MaterialButton buttonadd, buttonminus, buttondivide, buttonmulti, buttonequals;
    MaterialButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    MaterialButton buttonback, buttondot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.textView4);
        solution = findViewById(R.id.textView6);

        assignId(buttonac,R.id.btnac);
        assignId(buttonbracketopen,R.id.open);
        assignId(buttonbracketclose,R.id.close);
        assignId(buttonadd,R.id.btnadd);
        assignId(buttonminus,R.id.btnminus);
        assignId(buttondivide,R.id.btndiv);
        assignId(buttonmulti,R.id.btnmul);
        assignId(buttonequals,R.id.btnequal);
        assignId(button1,R.id.btn1);
        assignId(button2,R.id.btn2);
        assignId(button3,R.id.btn3);
        assignId(button4,R.id.btn4);
        assignId(button5,R.id.btn5);
        assignId(button6,R.id.btn6);
        assignId(button7,R.id.btn7);
        assignId(button8,R.id.btn8);
        assignId(button9,R.id.btn9);
        assignId(buttonback,R.id.btnback);
        assignId(buttondot,R.id.btndot);
        assignId(button0,R.id.btn0);
    }
    void assignId(MaterialButton btn,int id)
    {
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate=solution.getText().toString();
        if (buttonText.equals("AC"))
        {
            solution.setText("");
            result.setText("0");
            return;
        }
        if(buttonText.equals("="))
        {
            solution.setText(result.getText());
            return;
        }
        if(buttonText.equals("C"))
        {
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else
        {
            dataToCalculate=dataToCalculate+buttonText;
        }
        solution.setText(dataToCalculate);
        String finalResult=getResult(dataToCalculate);
        if(!finalResult.equals("error"))
        {
            result.setText(finalResult);
        }
    }
    String getResult(String data){
       try{
           Context context=Context.enter();
           context.setOptimizationLevel(-1);
           Scriptable scriptable=context.initSafeStandardObjects();
           String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           if(finalResult.endsWith(".0"))
           {
               finalResult=finalResult.replace(".0","");
           }
           return finalResult;
       }catch (Exception e){
           return "error";
       }
    }

}