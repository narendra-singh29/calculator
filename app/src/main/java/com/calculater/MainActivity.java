package com.calculater;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView displayText, displayResult;
    Button buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven,
            buttonEight, buttonNine, buttonZero;

    Button buttonClear, buttonOpen, buttonClose, buttonPer, buttonMul, buttonSub, buttonAdd,
    buttonEqual, buttonDiv, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayResult = findViewById(R.id.dispalyResult);
        displayText = findViewById(R.id.dispalyText);

        assingId(buttonOne, R.id.buttonOne);
        assingId(buttonTwo, R.id.buttonTwo);
        assingId(buttonThree, R.id.buttonThree);
        assingId(buttonFour, R.id.buttonFour);
        assingId(buttonFive, R.id.buttonFive);
        assingId(buttonSix, R.id.buttonSix);
        assingId(buttonSeven, R.id.buttonSeven);
        assingId(buttonEight, R.id.buttonEight);
        assingId(buttonNine, R.id.buttonNine);
        assingId(buttonZero, R.id.buttonZero);
        assingId(buttonClear, R.id.buttonClear);
        assingId(buttonOpen, R.id.buttonOpen);
        assingId(buttonClose, R.id.buttonClose);
        assingId(buttonPer, R.id.buttonPer);
        assingId(buttonMul, R.id.buttonMul);
        assingId(buttonSub, R.id.buttonSub);
        assingId(buttonAdd, R.id.buttonAdd);
        assingId(buttonEqual, R.id.buttonEqual);
        assingId(buttonDiv, R.id.buttonDiv);
        assingId(buttonDot, R.id.buttonDot);

    }



    void assingId(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        String data = displayText.getText().toString();
        if(buttonText.equals("=")){
            displayText.setText(displayResult.getText());
            return;
        }
        if(buttonText.equals("C")){
            data = data.substring(0, (data.length()-1));
        }
        else {
            data = data + buttonText;
        }
        displayText.setText(data);
        String finalResullt = getResult(data);
        if(!finalResullt.equals("ERROR")){
            displayResult.setText(finalResullt);
        }
    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "ERROR";
        }
    }
}