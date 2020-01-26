package com.example.task3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NoteActivity extends AppCompatActivity {

    private static final String ID_KEY = "ID_KEY";

    public static Intent getIntent(final Context context, final long id) {
        final Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(ID_KEY, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        final long id = getIntent().getLongExtra(ID_KEY, -1);
        final Note note = NoteRepository.getNoteWithId(id);

        setTitle(note.getTitle());

        final TextView noteTextTextView = findViewById(R.id.noteTextTextView);
        noteTextTextView.setText(note.getText());

        final ImageView noteImageImageView = findViewById(R.id.noteImageImageView);
        noteImageImageView.setImageResource(note.getDrawableIdRes());
    }
}
