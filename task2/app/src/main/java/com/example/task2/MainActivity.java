package com.example.task2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(getString(R.string.main_toolbar_title));

        final Button emailButton = findViewById(R.id.emailButton);
        emailButton.setOnClickListener(this);
        final CardView infoCardView = findViewById(R.id.infoCardView);
        infoCardView.setOnClickListener(this);

        final TextView dateTextView = findViewById(R.id.dateTextView);
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        dateTextView.setText(dateText);
    }

    @Override
    public void onClick(final View view) {
        final Intent intent;

        switch (view.getId()) {
            case R.id.emailButton: {
                String mailTo = "mailto:" + Uri.encode(getString(R.string.email_text)) +
                        "?subject=" + Uri.encode(getString(R.string.subject_text));

                intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(mailTo));
                break;
            }
            case R.id.infoCardView: {
                intent = new Intent(this, DetailActivity.class);
                break;
            }
            default:
                throw new IllegalArgumentException("Unknown button");
        }

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Error: Activity not found", Toast.LENGTH_SHORT).show();
        }
    }
}
