package pl.edu.pb.tetris_sm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pl.edu.pb.tetris_sm.api.LocalScoreListAdapter;
import pl.edu.pb.tetris_sm.db.AppDatabase;
import pl.edu.pb.tetris_sm.db.Score;

public class HighScoresActivity extends AppCompatActivity {

    private ListView listViewScoreList;
    private List<Score> scoreList;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        listViewScoreList = (ListView) findViewById(R.id.listViewScoreListLocal);
        loadUserList();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadUserList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        scoreList = db.ScoreDao().getAllScores();
        // sort our list
        scoreList.sort(Comparator.comparing(Score::getScore));
        Collections.reverse(scoreList);

        listViewScoreList.setAdapter(new LocalScoreListAdapter(getApplicationContext(), scoreList));
    }


}