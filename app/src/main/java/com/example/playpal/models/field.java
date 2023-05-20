package com.example.playpal.models;

public class field {

    Integer fieldId;
    String fieldName;
    String fieldLocation;
    byte[] fieldPicture;
    double latitude, longitude;

    public field(Integer fieldId, String fieldName, String fieldLocation, byte[] fieldPicture, double latitude, double longitude) {
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.fieldLocation = fieldLocation;
        this.fieldPicture = fieldPicture;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldLocation() {
        return fieldLocation;
    }

    public void setFieldLocation(String fieldLocation) {
        this.fieldLocation = fieldLocation;
    }

    public byte[] getFieldPicture() {
        return fieldPicture;
    }

    public void setFieldPicture(byte[] fieldPicture) {
        this.fieldPicture = fieldPicture;
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
}
