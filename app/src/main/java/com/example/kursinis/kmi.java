package com.example.kursinis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class kmi extends AppCompatActivity {

    EditText height ,weight;
    ImageView female, male;
    LinearLayout malelayout,femalelayout;
    Button calculate_btn;
    TextView result,condition;

    float h=0,w=0,kmi=0;
    String user="0";
    String res=" ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kmi);

        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        malelayout=findViewById(R.id.mlayout);
        femalelayout=findViewById(R.id.flayout);
        calculate_btn=findViewById(R.id.calculate_kmi);
        result=findViewById(R.id.kmi_result);
        condition=findViewById(R.id.condition);

        malelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setColorFilter(getResources().getColor(R.color.purple_700));
                female.setColorFilter(getResources().getColor(R.color.gray));
                user="Vyras";

            }
        });
        femalelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setColorFilter(getResources().getColor(R.color.purple_700));
                male.setColorFilter(getResources().getColor(R.color.gray));
                user="Moteris";
            }
        });

        calculate_btn.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = height.getText().toString();
                String str2 = weight.getText().toString();
                if (user.equals("0")) {
                    Toast.makeText(kmi.this, "Prašau pasirinkite lyti!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(str1)) {
                    height.setError("Įveskite savo ūgį!");
                    height.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(str2)) {
                    weight.setError("Įveskite savo svorį!");
                    weight.requestFocus();
                    return;
                } else {
                    calculate();
                }
            }
        });
    }

    private void calculate(){
            h=Float.parseFloat(height.getText().toString());
            w=Float.parseFloat(weight.getText().toString());
            float hm;
            hm=h/100;
            kmi=(w/(hm*hm));
            result.setText(Float.toString(kmi));
            if (kmi>=30){
                res="Nutukimas";
                condition.setText(res);
            }
            else if (kmi>=25){
                res="Nutukes";
                condition.setText(res);
            }
            else if (kmi>=18.5){
                res="Idealus svoris";
                condition.setText(res);
            }else{
                res="Svorio nepakankamumas";
                condition.setText(res);
            }


        }

}