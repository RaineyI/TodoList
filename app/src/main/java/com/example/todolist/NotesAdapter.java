package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> notes = new ArrayList<>();

    private OnNoteClickListener onNoteClickListener;
    public void setOnClickListener(OnNoteClickListener onClickListener){
        this.onNoteClickListener = onClickListener;
    }

    public List<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.note_item,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.textViewNotes.setText(note.getText());

        int colorResId;
        switch (note.getPriority()){
            case 0: colorResId = R.color.green;
                break;
            case 1: colorResId = R.color.orange;
                break;
            default: colorResId = R.color.red;
                break;
        }

        int color = ContextCompat.getColor(holder.itemView.getContext(), colorResId);
        holder.textViewNotes.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNotes;
        public NotesViewHolder(@NonNull View itemView){
            super(itemView);
            textViewNotes = itemView.findViewById(R.id.textViewNote);
        }
    }


    interface OnNoteClickListener {
        void onNoteClick(Note note);
    }
}