package com.example.mydoc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Vitals extends AppCompatActivity {

    EditText PatientName,Visit_Date,Height,Weight,BMI;
    Button btnClose,btnSave;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);


        PatientName = findViewById(R.id.PatientName);
        Visit_Date =findViewById(R.id.Visit_Date);
        Height =findViewById(R.id.Height);
        Weight = findViewById(R.id.Weight);
        BMI = findViewById(R.id.BMI);

        //        ......................
        btnClose= findViewById(R.id.btnClose);
        btnSave= findViewById(R.id.btnSave);

        //....................db..........
        DB= new DBHelper(this);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                String PatientNamet = PatientName.getText().toString();
                DateFormat Visit_Datet= DateFormat.getInstanceForSkeleton(Visit_Date.getText().toString());
                String Heightt = Height.getText().toString();
                String Weightt = Weight.getText().toString();
                DateFormat BMIt = DateFormat.getInstanceForSkeleton(BMI.getText().toString());
                Boolean savedata = DB.saveuserdata(PatientNamet,Visit_Datet,Heightt,Weightt,BMIt);
                if(TextUtils.isEmpty(PatientNamet)){
                    Toast.makeText(Vitals.this,"Add name",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    if(savedata==true){
                        Toast.makeText(Vitals.this,"save user",Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(Vitals.this,Visit.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Vitals.this,"exits user,",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}