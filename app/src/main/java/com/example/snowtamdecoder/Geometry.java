package com.example.snowtamdecoder;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Geometry {

    @SerializedName("type")
    private String type;
    @SerializedName("coordinates")
    private List<Double> coordinates;

    public Geometry(String type, List<Double> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }
}
