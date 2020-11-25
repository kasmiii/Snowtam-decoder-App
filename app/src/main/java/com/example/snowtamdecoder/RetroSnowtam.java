package com.example.snowtamdecoder;

import com.google.gson.annotations.SerializedName;

public class RetroSnowtam {

    @SerializedName("id")
    private String id;
    @SerializedName("entity")
    private String entity;
    @SerializedName("status")
    private String status;
    @SerializedName("Qcode")
    private String Qcode;
    @SerializedName("Area")
    private String Area;
    @SerializedName("SubArea")
    private String SubArea;
    @SerializedName("Condition")
    private String Condition;
    @SerializedName("Subject")
    private String Subject;
    @SerializedName("Modifier")
    private String Modifier;
    @SerializedName("message")
    private String message;
    @SerializedName("startdate")
    private String startdate;
    @SerializedName("enddate")
    private String enddate;
    @SerializedName("all")
    private String all;
    @SerializedName("location")
    private String location;
    @SerializedName("isICAO")
    private boolean isICAO;
    @SerializedName("Created")
    private String Created;
    @SerializedName("key")
    private String key;
    @SerializedName("type")
    private String type;
    @SerializedName("StateCode")
    private String StateCode;
    @SerializedName("StateName")
    private String StateName;
    @SerializedName("criticality")
    private int criticality;

    public RetroSnowtam(String id, String entity, String status, String qcode, String area, String subArea, String condition, String subject, String modifier, String message, String startdate, String enddate, String all, String location, boolean isICAO, String created, String key, String type, String stateCode, String stateName, int criticality) {
        this.id = id;
        this.entity = entity;
        this.status = status;
        Qcode = qcode;
        Area = area;
        SubArea = subArea;
        Condition = condition;
        Subject = subject;
        Modifier = modifier;
        this.message = message;
        this.startdate = startdate;
        this.enddate = enddate;
        this.all = all;
        this.location = location;
        this.isICAO = isICAO;
        Created = created;
        this.key = key;
        this.type = type;
        StateCode = stateCode;
        StateName = stateName;
        this.criticality = criticality;
    }

    //Retrieve the data using setter/getter methods//


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQcode() {
        return Qcode;
    }

    public void setQcode(String qcode) {
        Qcode = qcode;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getSubArea() {
        return SubArea;
    }

    public void setSubArea(String subArea) {
        SubArea = subArea;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getModifier() {
        return Modifier;
    }

    public void setModifier(String modifier) {
        Modifier = modifier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isICAO() {
        return isICAO;
    }

    public void setICAO(boolean ICAO) {
        isICAO = ICAO;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String created) {
        Created = created;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStateCode() {
        return StateCode;
    }

    public void setStateCode(String stateCode) {
        StateCode = stateCode;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public int getCriticality() {
        return criticality;
    }

    public void setCriticality(int criticality) {
        this.criticality = criticality;
    }
}
