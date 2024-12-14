package com.example.notes;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class Edit_page extends AppCompatActivity {

    ImageView editback;
    Button editsave, editdelete;
    Modelclass model;
    TextInputEditText edititle, editdescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_page);

        editback = findViewById(R.id.editback);
        edititle = findViewById(R.id.edititle);
        editdescription = findViewById(R.id.editdescription);
        editdelete = findViewById(R.id.editdelete);
        editsave = findViewById(R.id.editsave);
        editdelete = findViewById(R.id.editdelete);

        int cid = getIntent().getIntExtra("cid", 90);

        model = (Modelclass) getIntent().getSerializableExtra("UserModel");
        int userid = getIntent().getIntExtra("uid", 20);

        edititle.setText(model.getTittle());
        editdescription.setText(model.getDiscription());

        editback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Edit_page.this, NotesShow.class).putExtra("id", userid));
                finish();
            }
        });

        editsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mydatabase mydatabase = new Mydatabase(Edit_page.this);
                mydatabase.editdata(edititle.getText().toString(), editdescription.getText().toString(), cid);

                startActivity(new Intent(Edit_page.this, NotesShow.class).putExtra("id", userid));
                finish();
            }
        });

        editdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(Edit_page.this);
                dialog.setContentView(R.layout.delete);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                dialog.setCancelable(false);
                TextView tidelete = dialog.findViewById(R.id.tidelete);
                Button cancel = dialog.findViewById(R.id.cancel);
                Button delete = dialog.findViewById(R.id.delete);

                tidelete.getText();

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Mydatabase mydatabase = new Mydatabase(Edit_page.this);
                        mydatabase.deletedata(cid);
                        startActivity(new Intent(Edit_page.this, NotesShow.class).putExtra("id", userid));
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}