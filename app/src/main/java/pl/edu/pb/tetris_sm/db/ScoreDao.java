package pl.edu.pb.tetris_sm.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ScoreDao {

    @Query("SELECT * FROM score")
    List<Score> getAllScores();

    @Insert
    void insertScore(Score... scores);

    @Delete
    void delete(Score score);

}
