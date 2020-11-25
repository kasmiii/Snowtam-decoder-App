package com.example.snowtamdecoder;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataSnotam {

    @GET("/dataservices/api/notams-realtime-list?api_key=d7408e20-2793-11eb-9558-d78cfc3b3b93&format=json&criticality=1&locations=:locations")
    Call<List<RetroSnowtam>> getStreams(@Query("locations") String location);

}
