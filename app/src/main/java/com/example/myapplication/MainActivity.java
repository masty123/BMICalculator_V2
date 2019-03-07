package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText height, weight;
    TextView textStatus, bmiNumber ;
    Button calculate;
    ImageView imageResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        textStatus = (TextView) findViewById(R.id.textStatus);
        bmiNumber = (TextView) findViewById(R.id.bmiNumber);
        calculate = (Button) findViewById(R.id.calculate);
        imageResult = (ImageView) findViewById(R.id.imageResult);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(weight.getWindowToken(), 0);

            }
        });

    }

    private void  calculateBMI(){
        String heightValue = height.getText().toString();
        String weightValue = weight.getText().toString();

        if (heightValue != null && !"".equals(heightValue) && weightValue != null && !"".equals(weightValue)) {
            float heightCal = Float.parseFloat(heightValue) / 100;
            float weightCal = Float.parseFloat(weightValue);
            float bmi = weightCal / (heightCal * heightCal);

            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi){
        String bmiLabel = "";
        imageResult.setVisibility(View.VISIBLE);
        if(bmi < 22){
            bmiLabel = "underweight";
            textStatus.setTextColor(Color.BLUE);
            bmiNumber.setTextColor(Color.BLUE);
            imageResult.setImageResource(R.drawable.skinny);

        }
        else if (bmi >= 22 && bmi <= 30){
            bmiLabel = "okay";
            textStatus.setTextColor(Color.GREEN);
            bmiNumber.setTextColor(Color.GREEN);
            imageResult.setImageResource(R.drawable.muscle);

        }
        else {
            bmiLabel = "Fat!";
            textStatus.setTextColor(Color.RED);
            bmiNumber.setTextColor(Color.RED);
            imageResult.setImageResource(R.drawable.fat);

        }

        textStatus.setText(bmiLabel);
        bmiNumber.setText("Your BMI is: "+Float.toString(bmi));

    }

}
