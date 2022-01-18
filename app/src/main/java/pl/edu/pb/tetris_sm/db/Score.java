package pl.edu.pb.tetris_sm.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Score {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "score")
    public int score;

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
