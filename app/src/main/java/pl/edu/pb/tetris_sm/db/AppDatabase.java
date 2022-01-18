package pl.edu.pb.tetris_sm.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Score.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ScoreDao ScoreDao();
    private static AppDatabase INSTANCE;
    public static AppDatabase getDbInstance(Context context ) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "DB_HIGHSCORES").allowMainThreadQueries().build();

        }
        return INSTANCE;
    }
}
