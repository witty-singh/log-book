package com.example.log_book;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {
    private LayoutInflater inflater;
    private List<Note> notes;
    private List<Note> noteall;
    public static Long Lid;

    Adapter(Context context,List<Note> notes){
        this.inflater=LayoutInflater.from(context);
        this.notes=notes;
        this.noteall=new ArrayList<Note>(notes);
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.custom_list_view,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int i) {


        String name=notes.get(i).getNAME().toUpperCase();
        Log.d("search", "notes values"+notes);
        Log.d("search", "noteall values"+noteall);

        String charge=notes.get(i).getCHARGES();
        String date=notes.get(i).getDATE();
        String symptom=notes.get(i).getSYMPTOM();
        String medicine=notes.get(i).getMEDICINE();
        String age=notes.get(i).getAGE();
        viewHolder.nNAME.setText(name);
        viewHolder.nCHARGE.setText(charge);
        viewHolder.nDATE.setText(date);
        viewHolder.nID.setText(String.valueOf(notes.get(i).getID()));
        viewHolder.nSYMPTOM.setText(symptom);
        viewHolder.nMEDICINE.setText(medicine);
        viewHolder.nAGE.setText(age);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Note> filteredList = new ArrayList<Note>();
            if(constraint.toString().isEmpty()) {
                filteredList.addAll(noteall);
            }
            else{
                for(Note n:noteall){
                    if(n.getNAME().toUpperCase().contains(constraint.toString().toUpperCase())){
                        filteredList.add(n);
                    }
                }
            }

            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notes.clear();
            notes.addAll((Collection<? extends Note>) results.values);
            notifyDataSetChanged();

        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nNAME,nDATE,nCHARGE,nID,nSYMPTOM,nMEDICINE,nAGE;

        public ViewHolder(@NonNull  final View itemView) {
            super(itemView);
            nNAME=itemView.findViewById(R.id.NAME);
            nCHARGE=itemView.findViewById(R.id.CHARGE);
            nDATE=itemView.findViewById(R.id.DATE);
            nID=itemView.findViewById(R.id.patientID);
            nSYMPTOM=itemView.findViewById(R.id.SYMPTOM);
            nMEDICINE=itemView.findViewById(R.id.MEDICINE);
            nAGE=itemView.findViewById(R.id.AGE);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    noteDatabase db=new noteDatabase(v.getContext());
                    Lid= notes.get(getAdapterPosition()).getID();
                    db.deleteNote(Lid);
                    Toast.makeText(v.getContext(),"deleted",Toast.LENGTH_LONG).show();

                    return true;
                }
            });
            /*
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     noteDatabase db=new noteDatabase(v.getContext());
                     Lid= notes.get(getAdapterPosition()).getID();

                     db.deleteNote(Lid);

                     Toast.makeText(v.getContext(),"deleted",Toast.LENGTH_LONG).show();

                 }
             });
            */


        }
    }
}
