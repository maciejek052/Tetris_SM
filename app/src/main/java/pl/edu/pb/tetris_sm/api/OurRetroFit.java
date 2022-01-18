package pl.edu.pb.tetris_sm.api;

import java.util.List;

import pl.edu.pb.tetris_sm.api.OurDataSet;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OurRetroFit {

    @POST("/highScores")
    Call<OurDataSet> PostData(@Body OurDataSet ourDataSet);

    @GET("/highScores")
    Call<List<OurDataSet>> findAll();


}
