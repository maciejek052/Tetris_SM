package pl.edu.pb.tetris_sm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pl.edu.pb.tetris_sm.api.OurDataSet;
import pl.edu.pb.tetris_sm.api.OurRetroFit;
import pl.edu.pb.tetris_sm.api.ScoreListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HighScoresApiActivity extends AppCompatActivity {

    private ListView listViewScoreList;
    private List<OurDataSet> ourDataSetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores_api);

        listViewScoreList = (ListView) findViewById(R.id.listViewScoreList);
        GetData();


    }

    public void GetData() {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tetris-rest.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OurRetroFit ourRetroFit = retrofit.create(OurRetroFit.class);
        Call call = ourRetroFit.findAll();
        call.enqueue(new Callback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call call, Response response) {
                ourDataSetList = (List<OurDataSet>) response.body();

                // sort our list
                ourDataSetList.sort(Comparator.comparing(OurDataSet::getScore));
                Collections.reverse(ourDataSetList);

                listViewScoreList.setAdapter(new ScoreListAdapter(getApplicationContext(), ourDataSetList));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast toast = Toast.makeText(context, R.string.failureGettingDataFromServer, duration);
                toast.show();
            }
        });

    }

}