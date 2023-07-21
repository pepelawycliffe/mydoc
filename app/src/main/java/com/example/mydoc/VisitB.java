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

public class VisitB extends AppCompatActivity {

    EditText PatientName,Visit_Date;
    Button btnClose,btnSave;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_b);

        PatientName = findViewById(R.id.PatientName);
        Visit_Date =findViewById(R.id.Visit_Date);

        //        ......................
        btnClose= findViewById(R.id.btnClose);
        btnSave= findViewById(R.id.btnSave);
//...................................DB
        DB= new DBHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                String PatientNamet = PatientName.getText().toString();
                DateFormat Visit_Datet= DateFormat.getInstanceForSkeleton(Visit_Date.getText().toString());
                Boolean savedata = DB.saveuserdata(PatientNamet,Visit_Datet,null,null,null);
                if(TextUtils.isEmpty(PatientNamet)){
                    Toast.makeText(VisitB.this,"Add name",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    if(savedata==true){
                        Toast.makeText(VisitB.this,"save user",Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(VisitB.this,PatientListing.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(VisitB.this,"exits user,",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}