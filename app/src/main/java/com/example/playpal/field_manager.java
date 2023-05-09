package com.example.playpal;

import java.util.ArrayList;

public class field_manager {
    ArrayList<field_class> fields;

    public field_manager(){
        fields = new ArrayList<>();
    }

    public void addField(field_class field_class){
        fields.add(field_class);
    }

    public void addRoomToField(room_class room_class, String fieldName){
        for(field_class field_class : fields){
            if(field_class.getFieldName().equals(fieldName)){
                field_class.addRoom(room_class);
                break;
            }
        }
    }

}
