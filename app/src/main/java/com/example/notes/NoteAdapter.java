package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.viewHolder> {

    Context context;
    int uid;
    public NoteAdapter(ArrayList<Modelclass> arrayList, Context context, int uid) {
        this.arrayList = arrayList;
        this.context = context;
        this.uid = uid;
    }

    ArrayList <Modelclass>  arrayList = new ArrayList<>();
    @NonNull
    @Override
    public NoteAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.show,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.viewHolder holder, int position) {
        Modelclass model =arrayList.get(position);
            holder.tvTittle.setText(model.getTittle());
            holder.discription.setText(model.getDiscription());

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,Edit_page.class);
                intent.putExtra("UserModel",model).putExtra("cid",arrayList.get(position).getId()).putExtra("uid",uid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        TextView tvTittle,discription;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tvTittle = itemView.findViewById(R.id.tvTittle);
            discription = itemView.findViewById(R.id.discription);

            cardview = itemView.findViewById(R.id.cardview);

        }
    }
}
