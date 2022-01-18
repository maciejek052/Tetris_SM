package pl.edu.pb.tetris_sm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pl.edu.pb.tetris_sm.api.OurDataSet;
import pl.edu.pb.tetris_sm.api.OurRetroFit;
import pl.edu.pb.tetris_sm.db.AppDatabase;
import pl.edu.pb.tetris_sm.db.Score;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GameOverActivity extends AppCompatActivity {

    public static int finalScore;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        EditText nameBox=findViewById(R.id.nameTextbox);
        if (finalScore == 0)
            nameBox.setVisibility(View.GONE);

        // change score text
        TextView scoreValue = (TextView) findViewById(R.id.finalScoreValue);
        scoreValue.setText(Integer.toString(finalScore));

        // cancel button
        Button cancel = findViewById((R.id.cancelSavingScoreButton));
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // send to server button
        Button sendApi = findViewById((R.id.saveHighScoreButton));
        if (finalScore == 0)
            sendApi.setVisibility(View.GONE);
        sendApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostData();
            }
        });

        // save in local db button
        Button saveDb = findViewById((R.id.saveHighScoreLocallyButton));
        if (finalScore == 0)
            saveDb.setVisibility(View.GONE);
        saveDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    // method to POST score to server
    public void PostData() {

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        EditText nameBox=findViewById(R.id.nameTextbox);
        String name=nameBox.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tetris-rest.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OurRetroFit ourRetroFit = retrofit.create(OurRetroFit.class);
        OurDataSet ourDataSet = new OurDataSet(name, finalScore);
        Call<OurDataSet> call = ourRetroFit.PostData(ourDataSet);
        call.enqueue(new Callback<OurDataSet>() {
            @Override
            public void onResponse(Call<OurDataSet> call, Response<OurDataSet> response) {
                Toast toast = Toast.makeText(context, R.string.savedToServerToast, duration);
                toast.show();
            }

            @Override
            public void onFailure(Call<OurDataSet> call, Throwable t) {
                Toast toast = Toast.makeText(context, R.string.failureSavingToServerToast, duration);
                toast.show();
            }
        });
    }


    // method to save score in local database
    private void saveData() {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        EditText nameBox=findViewById(R.id.nameTextbox);
        String name=nameBox.getText().toString();

        AppDatabase db = AppDatabase.getDbInstance(context);
        Score score = new Score();
        score.score = finalScore;
        score.name = name;
        db.ScoreDao().insertScore(score);
        Toast toast = Toast.makeText(context, R.string.savedToLocalDbToast, duration);
        toast.show();
    }


}