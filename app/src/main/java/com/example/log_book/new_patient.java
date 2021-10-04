package com.example.log_book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.R.color;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class new_patient extends AppCompatActivity {
    EditText Name,Medicine,Symptom,Charge,Date,age;
    Calendar c;
    String todaysDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);
        Name=findViewById(R.id.name);
        Medicine=findViewById(R.id.medi);
        Symptom=findViewById(R.id.symp);
        Charge=findViewById(R.id.charges);
        Date=findViewById(R.id.date);
        age=findViewById(R.id.age);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#30111E"));

        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("New Patient");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0){
                    getSupportActionBar().setTitle(s);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        c=Calendar.getInstance();
        todaysDate=c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);
        Date.setText(todaysDate);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.save,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.save){
            Note note=new Note(Name.getText().toString(),Medicine.getText().toString(),Symptom.getText().toString(),Charge.getText().toString(),todaysDate,age.getText().toString());
            noteDatabase db=new noteDatabase(this);
            Long id=db.AddNote(note);
            Log.d("inserted", " id->"+ id);
            Toast.makeText(this,"saved",Toast.LENGTH_LONG).show();
            gotoMain();
        }





        return super.onOptionsItemSelected(item);
    }

    private void gotoMain() {
        Intent i= new Intent(this,MainActivity.class);
        startActivity(i);
    }

}