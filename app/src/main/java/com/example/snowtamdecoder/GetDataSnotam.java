package com.example.snowtamdecoder;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataSnotam {

    @GET("/dataservices/api/notams-realtime-list?api_key=f9bbf130-3b23-11eb-9f83-5930c6e3b16a&format=json&criticality=1&locations=:locations")
    Call<List<RetroSnowtam>> getStreams(@Query("locations") String location);

    @GET("/dataservices/api/indicators-list?api_key=f9bbf130-3b23-11eb-9f83-5930c6e3b16a&state=&airports=:airports&format=json")
    Call<List<AerodromeInformation>> getAerodromeInformation(@Query("airports")String airports);

}
