package com.example.notes;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;

public class NotesShow extends AppCompatActivity {

    Button add;
    RecyclerView list; SearchView search ;
    ArrayList<Modelclass> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_show);
        add = findViewById(R.id.add);
        list = findViewById(R.id.list);
        search = findViewById(R.id.search);

        int texthintColor = Color.BLACK;
        int textColor = Color.BLACK;

        // Access the SearchView text elements
        TextView searchText = search.findViewById(androidx.appcompat.R.id.search_src_text);
        if (searchText != null) {
            searchText.setHintTextColor(texthintColor);
            searchText.setTextColor(textColor);
        }
        ArrayList<Modelclass> searchList = new ArrayList<>();

        list.setLayoutManager(new LinearLayoutManager(this));

        int userid = getIntent().getIntExtra("id", 10);


        Mydatabase mydatabase = new Mydatabase(this);
        Cursor cursor = mydatabase.data(userid);


        while (cursor.moveToNext()) {
            arrayList.add(new Modelclass(cursor.getString(1), cursor.getString(2), cursor.getInt(0)));
        }
        cursor.close();

////search button clicked
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("===", "onQueryTextSubmit: " + query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("===", "onQueryTextChange: " + newText);
                searchList.clear();
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getTittle().contains(newText)) {
                        searchList.add(arrayList.get(i));
                    }
                }
                list.setAdapter(new NoteAdapter(searchList, NotesShow.this, userid));
                return true;
            }
        });

        NoteAdapter noteAdapter = new NoteAdapter(arrayList, this, userid);
        list.setAdapter(noteAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotesShow.this, AddNote.class).putExtra("id", userid));
            }
        });
    }
}