package com.example.snowtamdecoder;


import com.google.gson.annotations.SerializedName;

public class AerodromeInformation {

        @SerializedName("countryName")
        private String countryName;
        @SerializedName("countryCode")
        private String countryCode;
        @SerializedName("airportName")
        private String airportName;
        @SerializedName("cityName")
        private String cityName;
        @SerializedName("latitude")
        private double latitude;
        @SerializedName("longitude")
        private double longitude;
        @SerializedName("airportCode")
        private String airportCode;
        @SerializedName("geometry")
        private Geometry geometry;


    public AerodromeInformation(String countryName, String countryCode, String airportName, String cityName, double latitude, double longitude, String airportCode, Geometry geometry) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.airportName = airportName;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.airportCode = airportCode;
        this.geometry = geometry;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "AerodromeInformation{" +
                "countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", airportName='" + airportName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", airportCode='" + airportCode + '\'' +
                ", geometry=" + geometry +
                '}';
    }
}
