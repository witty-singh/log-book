package com.example.log_book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Note> notes;
    Adapter adapter;
    LinearLayoutManager llm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        noteDatabase db=new noteDatabase(this);
        notes=db.getnotes();
        recyclerView =findViewById(R.id.recycler);
        llm=new LinearLayoutManager(this);
        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);
        adapter=new Adapter(this,notes);
        recyclerView.setAdapter(adapter);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#30111E"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("Dr SS REKHRAJ");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.add,menu);
        MenuItem item=menu.findItem(R.id.app_bar_search);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add) {
            Intent intent = new Intent(this, new_patient.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.sortdown){
            llm.setReverseLayout(false);
            llm.setStackFromEnd(false);
        }

        if(item.getItemId()==R.id.sortup){
            llm.setReverseLayout(true);
            llm.setStackFromEnd(true);
        }
        return super.onOptionsItemSelected(item);
    }


}