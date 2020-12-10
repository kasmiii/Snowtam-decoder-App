package com.example.snowtamdecoder;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataSnotam {

    @GET("/dataservices/api/notams-realtime-list?api_key=2c993720-3a52-11eb-aa88-ab02fc941694&format=json&criticality=1&locations=:locations")
    Call<List<RetroSnowtam>> getStreams(@Query("locations") String location);

    @GET("/dataservices/api/indicators-list?api_key=2c993720-3a52-11eb-aa88-ab02fc941694&state=&airports=:airports&format=json")
    Call<List<AerodromeInformation>> getAerodromeInformation(@Query("airports")String airports);

}
