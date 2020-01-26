package com.example.task3;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private long id;
    private CardView noteCardView;
    private TextView noteDateTextView;
    private TextView noteTextTextView;
    private TextView noteTitleTextView;
    private ImageView noteImageImageView;

    public NoteViewHolder(
            final View itemView,
            final NoteAdapter.Listener listener
    ) {
        super(itemView);

        noteCardView = itemView.findViewById(R.id.noteCardView);
        noteCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (listener != null) {
                    listener.onNoteClick(id);
                }
            }
        });

        noteDateTextView = itemView.findViewById(R.id.noteDateTextView);
        noteTextTextView = itemView.findViewById(R.id.noteTextTextView);
        noteTitleTextView = itemView.findViewById(R.id.noteTitleTextView);
        noteImageImageView = itemView.findViewById(R.id.noteImageImageView);
    }

    public void bind(final Note note) {
        id = note.getId();

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        String dateText = dateFormat.format(note.getDate());
        noteDateTextView.setText(dateText);

        noteTextTextView.setText(note.getText());
        noteTitleTextView.setText(note.getTitle());
        noteImageImageView.setImageResource(note.getDrawableIdRes());
    }
}
