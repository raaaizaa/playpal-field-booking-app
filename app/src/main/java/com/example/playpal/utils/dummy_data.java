package com.example.playpal.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.playpal.R;

import java.io.ByteArrayOutputStream;

public class dummy_data {

    field_database_helper fielddb;
    room_database_helper roomdb;
    player_database_helper playerdb;
    Context context;

    public dummy_data(Context context){
        this.context = context;
        fielddb = new field_database_helper(context);
        roomdb = new room_database_helper(context);
        playerdb = new player_database_helper(context);
    }

    public void insertData(){

        int drawable1 = R.drawable.champion_futsal;
        byte[] drawable1Bytes = getDrawableBytes(context, drawable1);
        fielddb.insertField(1, "Champion Futsal", "Jl. Rw. Belong No.13, RT.1/RW.9, Kb. Jeruk, Kec. Kb. Jeruk, Kota Jakarta Barat, Daerah Khusus Ibukota Jakarta 11530", drawable1Bytes, -6.2053224104486695, 106.78294276749396);

        int drawable2 = R.drawable.terminal_futsal;
        byte[] drawable2Bytes = getDrawableBytes(context, drawable2);
        fielddb.insertField(2, "Terminal Futsal", "Jl. Masjid Al Anwar No.Kav. 2, RT.2/RW.1, Sukabumi Utara, Kec. Kb. Jeruk, Kota Jakarta Barat, Daerah Khusus Ibukota Jakarta 11540", drawable2Bytes, -6.211057842500733, 106.78144920003493);

        int drawable3 = R.drawable.elang_futsal;
        byte[] drawable3Bytes = getDrawableBytes(context, drawable3);
        fielddb.insertField(3, "Elang Futsal", "Jl. Taman Mutiara Prima No.10, Kb. Jeruk, Kec. Kb. Jeruk, Kota Jakarta Barat, Daerah Khusus Ibukota Jakarta 11530", drawable3Bytes, -6.195245812037082, 106.77905580752721);

        roomdb.insertDummyRoom(101, 1, "join aja", "Futsal", "Champion Futsal");
        roomdb.insertDummyRoom(102, 1, "SPARRING BRIGEZ VS XTC", "Futsal", "Champion Futsal");
        roomdb.insertDummyRoom(103, 1, "FUTSAL PEMUDA BINUS REVOLUSIONER", "Futsal", "Champion Futsal");

        roomdb.insertDummyRoom(201, 2, "kuy", "Futsal", "Terminal Futsal");
        roomdb.insertDummyRoom(202, 2, "latihan kesebelasan NANKATSU SC", "Futsal", "Terminal Futsal");

        roomdb.insertDummyRoom(301, 3, "ngajedog", "Futsal", "Elang Futsal");

        playerdb.insertDummyPlayer(10101, 101, "fakhri");
        playerdb.insertDummyPlayer(10102, 101, "arvito");
        playerdb.insertDummyPlayer(10103, 101, "rasyid");
        playerdb.insertDummyPlayer(10104, 101, "kiting");

        playerdb.insertDummyPlayer(10201, 102, "ASEP BRIGEZ");
        playerdb.insertDummyPlayer(10202, 102, "SYARIF BRIGEZ");
        playerdb.insertDummyPlayer(10203, 102, "EKO BRIGEZ");
        playerdb.insertDummyPlayer(10204, 102, "SYARIFUDDIN XTC");
        playerdb.insertDummyPlayer(10205, 102, "TARMIJI XTC");
        playerdb.insertDummyPlayer(10206, 102, "JHON XTC");
        playerdb.insertDummyPlayer(10207, 102, "UDIN XTC");
        playerdb.insertDummyPlayer(10208, 102, "dadang");

        playerdb.insertDummyPlayer(10301, 103, "bian");
        playerdb.insertDummyPlayer(10302, 103, "ken");
        playerdb.insertDummyPlayer(10303, 103, "dyl");

        playerdb.insertDummyPlayer(20101, 201, "maman");
        playerdb.insertDummyPlayer(20102, 201, "arif");
        playerdb.insertDummyPlayer(20103, 201, "aqil");
        playerdb.insertDummyPlayer(20104, 201, "heru");

        playerdb.insertDummyPlayer(20201, 202, "GENZO WAKABAYASHI");
        playerdb.insertDummyPlayer(20202, 202, "TAKESHI KISHIDA");
        playerdb.insertDummyPlayer(20203, 202, "KOJI NISHIO");
        playerdb.insertDummyPlayer(20204, 202, "MASAO NAKAYAMA");
        playerdb.insertDummyPlayer(20205, 202, "HANJI URABE");
        playerdb.insertDummyPlayer(20206, 202, "SHINGO TAKASUGI");
        playerdb.insertDummyPlayer(20207, 202, "HAJIME TAKI");
        playerdb.insertDummyPlayer(20208, 202, "MAMORU IZAWA");
        playerdb.insertDummyPlayer(20209, 202, "TEPPEI KISUGI");
        playerdb.insertDummyPlayer(20210, 202, "TSUBASA OZORA");
        playerdb.insertDummyPlayer(20211, 202, "TARO MISAKI");

        playerdb.insertDummyPlayer(30101, 301, "James");

        Log.i("dummy_data.java", "Dummy data successfully inserted!");
    }

    public byte[] getDrawableBytes(Context context, int drawableId){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableId);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50, stream);
        return stream.toByteArray();
    }

}
