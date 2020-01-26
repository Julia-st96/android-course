package com.example.task3;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteRepository {

    private static final Map<Long, Note> NOTES = new HashMap<>();

    public static void initialize(final Context context) {
        Date date = new Date();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("notes.txt")))) {
            String note = reader.readLine();
            long id = 0;
            while (!TextUtils.isEmpty(note)) {
                final String[] noteElements = note.split(context.getString(R.string.delimiter));
                final String title = noteElements[0];
                final String drawableResName = noteElements[1];
                final String text = noteElements[2];

                Resources resources = context.getResources();
                final int drawableIdRes = resources.getIdentifier(drawableResName, "drawable",
                        context.getPackageName());

                NOTES.put(id, new Note(id, date, text, title, drawableIdRes));
                ++id;
                note = reader.readLine();
            }
        } catch (IOException e) {
        }
    }

    public static List<Note> getNotes() {
        return new ArrayList<>(NOTES.values());
    }

    public static Note getNoteWithId(final long id) {
        return NOTES.get(id);
    }
}
