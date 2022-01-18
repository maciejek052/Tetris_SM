package pl.edu.pb.tetris_sm.api;

public class OurDataSet {

    String name;
    int score;

    public OurDataSet() {

    }

    public OurDataSet(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
