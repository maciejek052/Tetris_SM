package pl.edu.pb.tetris_sm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HighScoresActivity extends AppCompatActivity {

    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        // create listview
        listview = findViewById(R.id.highScoresList);
        ArrayList<String> arrayList = new ArrayList<>();
        // populate listview with example data
        for (int i = 0; i < 25; i++) {
            arrayList.add(Integer.toString(i+10));
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listview.setAdapter(arrayAdapter);
        // back button
        Button back = findViewById((R.id.backButton3));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // clear button
        Button clear = findViewById((R.id.clearButton));
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "To kiedyś zostanie zaimplementowane, przysięgam!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}