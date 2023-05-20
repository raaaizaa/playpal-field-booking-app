package com.example.playpal.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.playpal.R;
import com.example.playpal.activities.field_detail;
import com.example.playpal.models.field;

import java.util.List;

public class field_adapter extends RecyclerView.Adapter<field_adapter.ViewHolder> {

    private final List<field> fields;
    private final Context context;
    private final String username;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView picture;
        private final TextView name, location;

        public ViewHolder(View view){
            super(view);
            picture = view.findViewById(R.id.field_picture);
            name = view.findViewById(R.id.field_name);
            location = view.findViewById(R.id.field_location);
        }
    }

    public field_adapter(List<field> fields, Context context, String username){
        this.fields = fields;
        this.context = context;
        this.username = username;
    }

    @Override
    public field_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.field_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(field_adapter.ViewHolder holder, int position) {
        field field = fields.get(position);
        holder.picture.setImageBitmap(BitmapFactory.decodeByteArray(field.getFieldPicture(), 0, field.getFieldPicture().length));
        holder.name.setText(field.getFieldName());
        holder.location.setText(field.getFieldLocation());

        holder.itemView.setOnClickListener(v -> {
            String fieldId = field.getFieldId().toString();
            String fieldName = field.getFieldName();
            String fieldLocation = field.getFieldLocation();
            double fieldLatitude = field.getLatitude();
            double fieldLongitude = field.getLongitude();

            Intent intent = new Intent(context, field_detail.class);
            intent.putExtra("fieldId", fieldId);
            intent.putExtra("fieldName", fieldName);
            intent.putExtra("fieldLocation", fieldLocation);
            intent.putExtra("username", username);
            intent.putExtra("fieldLatitude", fieldLatitude);
            intent.putExtra("fieldLongitude", fieldLongitude);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return fields.size();
    }
}
