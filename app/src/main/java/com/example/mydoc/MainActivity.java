package com.example.mydoc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Patient_Id,Registration_date,FirstName,LastName,Dob,Male,Female;
    Button btnClose,btnSave;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Patient_Id = findViewById(R.id.Patient_Id);
        Registration_date = findViewById(R.id.Registration_date);
        FirstName =findViewById(R.id.FirstName);
        LastName = findViewById(R.id.LastName);
        Dob=findViewById(R.id.Dob);
//        ......................
        btnClose= findViewById(R.id.btnClose);
        btnSave= findViewById(R.id.btnSave);
//...................................DB
        DB= new DBHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                String Patient_Idt = Patient_Id.getText().toString();
                DateFormat Registration_datet= DateFormat.getInstanceForSkeleton(Registration_date.getText().toString());
                String FirstNamet = FirstName.getText().toString();
                String LastNamet = LastName.getText().toString();
                DateFormat Dobt = DateFormat.getInstanceForSkeleton(Dob.getText().toString());
                Boolean savedata = DB.saveuserdata(Patient_Idt,Registration_datet,FirstNamet,LastNamet,Dobt);
                if(TextUtils.isEmpty(Patient_Idt)|| TextUtils.isEmpty(FirstNamet)){
                    Toast.makeText(MainActivity.this,"Add name",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    if(savedata==true){
                        Toast.makeText(MainActivity.this,"save user",Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(MainActivity.this,Vitals.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this,"exits user,",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}