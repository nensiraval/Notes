package com.example.notes;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.viewHolder> {

    Context context;
    int uid;
    int[] image;

    public NoteAdapter(ArrayList<Modelclass> arrayList, Context context, int uid, int[] image) {
        this.arrayList = arrayList;
        this.context = context;
        this.uid = uid;
        this.image = image;
    }

    ArrayList<Modelclass> arrayList = new ArrayList<>();

    @NonNull
    @Override
    public NoteAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.show, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.viewHolder holder, int position) {
        Modelclass model = arrayList.get(position);
        holder.tvTittle.setText(model.getTittle());
        holder.discription.setText(model.getDiscription());
//
//        holder.cardview.setBackgroundResource(image[position]);
//        Log.d("++++++", "background: " +  position);


        holder.draw.setBackgroundResource(image[position]);
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Edit_page.class);
                intent.putExtra("UserModel", model).putExtra("cid", arrayList.get(position).getId()).putExtra("uid", uid);
                context.startActivity(intent);
            }
        });

        holder.shareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");

                String title = arrayList.get(position).getTittle();
                String description = arrayList.get(position).getDiscription();

                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, description);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, title + "\n" + description);

                context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        TextView tvTittle, discription;
        LinearLayout draw;
        ImageView shareIcon;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tvTittle = itemView.findViewById(R.id.tvTittle);
            discription = itemView.findViewById(R.id.discription);
            cardview = itemView.findViewById(R.id.cardview);
            draw = itemView.findViewById(R.id.draw);
            shareIcon = itemView.findViewById(R.id.shareIcon);

        }
    }
}
