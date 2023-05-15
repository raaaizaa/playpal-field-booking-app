package com.example.playpal.models;

public class field {

    Integer fieldId;
    String fieldName;
    String fieldLocation;
    byte[] fieldPicture;

    public field(Integer fieldId, String fieldName, String fieldLocation, byte[] fieldPicture) {
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.fieldLocation = fieldLocation;
        this.fieldPicture = fieldPicture;
    }

    public field(String fieldName) {
        this.fieldName = fieldName;
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
}
