package pl.edu.pb.tetris_sm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Locale;


public class SettingsActivity extends AppCompatActivity {
    public static boolean musicEnabled = true;
    MainActivity.MyCallBack callBack;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        callBack = MainActivity.mCallback;
        // language button
        Button settings = findViewById(R.id.languageButton);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });

        // back button
        Button back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // dark mode switch
        SwitchCompat darkModeSwitch;
        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            darkModeSwitch.setChecked(true);
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }
            }
        });

        // music switch
        SwitchCompat musicSwitch;
        musicSwitch = findViewById(R.id.musicSwitch);
        if (musicEnabled == true)
            musicSwitch.setChecked(true);
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    musicEnabled = true;
                } else {
                    musicEnabled = false;
                }
            }
        });
    }

    // dialog to change language
    private void showChangeLanguageDialog() {
        final String[] listItems = {"English", "Deutsch", "Polski"};

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingsActivity.this);

        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        setLocale("en");
                        recreate();
                        callBack.refreshMainActivity();
                        break;
                    case 1:
                        setLocale("de");
                        recreate();
                        callBack.refreshMainActivity();
                        break;
                    case 2:
                        setLocale("pl");
                        recreate();
                        callBack.refreshMainActivity();
                        break;
                    default:
                        break;
                }
                dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
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

}