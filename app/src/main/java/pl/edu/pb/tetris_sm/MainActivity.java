package pl.edu.pb.tetris_sm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // callback to update language user changed in settings
    public interface MyCallBack {
        public void refreshMainActivity();
    }
    public static MyCallBack mCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        mCallback = new MyCallBack() {
            @Override
            public void refreshMainActivity() {
                MainActivity.this.recreate();
            }
        };

        // high scores button
        Button highScores = findViewById((R.id.highScoreButton));
        highScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HighScoresActivity.class);
                startActivity(intent);
            }
        });

        // settings button
        Button settings = findViewById(R.id.settingsButton);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        // about button
        Button about = findViewById((R.id.aboutButton));
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onResume(){
        super.onResume();
        // recreate();
    }

    // save current language
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        Log.d("setLocale", lang);
        editor.apply();
    }
    // load which language user selected earlier
    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        Log.d("loadLocale", language);
        setLocale(language);
    }

}