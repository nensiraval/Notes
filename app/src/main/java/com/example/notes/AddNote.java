package com.example.notes;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class AddNote extends AppCompatActivity {

    ImageView save,back;
    TextInputEditText tittle,distext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        save = findViewById(R.id.save);
        back = findViewById(R.id.back);
        tittle = findViewById(R.id.tittle);
        distext = findViewById(R.id.distext);


        int userid = getIntent().getIntExtra("id",20);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mydatabase mydatabase = new Mydatabase(AddNote.this);

                Boolean d = mydatabase.insertdata(tittle.getText().toString(),distext.getText().toString(),userid);
                    if(d) {
                        Toast.makeText(AddNote.this, "The data Successfully Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddNote.this, NotesShow.class).putExtra("id",userid));
                        finish();
                        Log.e("_+_+", "onClick: ");
                    }
                else
                {
                    Toast.makeText(AddNote.this,"The Edit Box is empty",Toast.LENGTH_SHORT).show();

                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNote.this, NotesShow.class).putExtra("id",userid));
                finish();
            }
        });

    }
}