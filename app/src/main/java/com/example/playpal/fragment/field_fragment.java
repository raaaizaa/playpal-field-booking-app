package com.example.playpal.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.playpal.R;
import com.example.playpal.adapters.field_adapter;
import com.example.playpal.utils.field_database_helper;

import java.io.ByteArrayOutputStream;

public class field_fragment extends Fragment {

    field_database_helper db;
    RecyclerView fieldRV;
    View view;
    String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_field, container, false);
        fieldRV = view.findViewById(R.id.fieldRV);
        fieldRV.setHasFixedSize(true);
        Bundle args = getArguments();

        if(args != null){
            username = args.getString("username");
        }

        insertData();
        setAdapter(username);
        Log.i("ini", "uy " + username);
        return view;
    }

    public void insertData(){
        Context context = getContext();
        assert context != null;
        db = new field_database_helper(context);

        int drawable1 = R.drawable.champion_futsal;
        byte[] drawable1Bytes = getDrawableBytes(context, drawable1);
        db.insertField(1, "Champion Futsal", "Jl. Rw. Belong No.13, RT.1/RW.9, Kb. Jeruk, Kec. Kb. Jeruk, Kota Jakarta Barat, Daerah Khusus Ibukota Jakarta 11530", drawable1Bytes);

        int drawable2 = R.drawable.terminal_futsal;
        byte[] drawable2Bytes = getDrawableBytes(context, drawable2);
        db.insertField(2, "Terminal Futsal", "Jl. Masjid Al Anwar No.Kav. 2, RT.2/RW.1, Sukabumi Utara, Kec. Kb. Jeruk, Kota Jakarta Barat, Daerah Khusus Ibukota Jakarta 11540", drawable2Bytes);

        int drawable3 = R.drawable.elang_futsal;
        byte[] drawable3Bytes = getDrawableBytes(context, drawable3);
        db.insertField(3, "Elang Futsal", "Jl. Taman Mutiara Prima No.10, Kb. Jeruk, Kec. Kb. Jeruk, Kota Jakarta Barat, Daerah Khusus Ibukota Jakarta 11530", drawable3Bytes);

    }

    public byte[] getDrawableBytes(Context context, int drawableId){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableId);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50, stream);
        return stream.toByteArray();
    }

    public void setAdapter(String username){
        fieldRV.setLayoutManager(new LinearLayoutManager((getContext()), LinearLayoutManager.HORIZONTAL, false));
        field_adapter adapter = new field_adapter(db.getAllFields(), getContext(), username);
        fieldRV.setAdapter(adapter);
    }
}