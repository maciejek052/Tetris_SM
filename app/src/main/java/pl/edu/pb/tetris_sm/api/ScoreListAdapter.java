package pl.edu.pb.tetris_sm.api;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.List;

import pl.edu.pb.tetris_sm.R;
import pl.edu.pb.tetris_sm.api.OurDataSet;

public class ScoreListAdapter extends ArrayAdapter<OurDataSet> {

    private Context context;
    private List<OurDataSet> ourDataSetList;

    public ScoreListAdapter(Context context, List<OurDataSet> ourDataSetList) {
        super(context, R.layout.score_row_layout, ourDataSetList);
        this.context = context;
        this.ourDataSetList = ourDataSetList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.score_row_layout, parent, false);

        OurDataSet ourDataSet = ourDataSetList.get(position);
        TextView textViewName = convertView.findViewById(R.id.textViewName);
        textViewName.setText(ourDataSet.getName());

        TextView textViewScore = convertView.findViewById(R.id.textViewScore);
        textViewScore.setText(String.valueOf(ourDataSet.getScore()));

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            textViewName.setTextColor(Color.parseColor("#b8b8b8"));
            textViewScore.setTextColor(Color.parseColor("#b8b8b8"));
        }
        else {
            textViewName.setTextColor(Color.parseColor("#757575"));
            textViewScore.setTextColor(Color.parseColor("#757575"));
        }

        return convertView;
    }
}
