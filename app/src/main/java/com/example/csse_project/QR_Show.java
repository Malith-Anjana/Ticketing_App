package com.example.csse_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QR_Show extends AppCompatActivity {
    //initiate variable
    ImageView ivOutput;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_show);

//        Action Bar title
        getSupportActionBar().hide();

        TextView start = findViewById(R.id.textVi2);
        TextView desti = findViewById(R.id.textVi4);
        TextView distance = findViewById(R.id.textVi10);
        TextView qty = findViewById(R.id.textVi8);
        TextView dates = findViewById(R.id.textVi6);

        ivOutput=findViewById(R.id.iv_output);

        Intent intent = getIntent();
        String starts = intent.getStringExtra("start");
        String end = intent.getStringExtra("end");
        String dist = intent.getStringExtra("dist");
        String qt = intent.getStringExtra("quant");
        String date = intent.getStringExtra("date");


        start.setText(starts);
        desti.setText(end);
        distance.setText(dist);
        qty.setText(qt);
        dates.setText(date);



        String details = "Start Destination" + starts +"\n" + "End Destination" + end +"\n"+ "Distance" + dist +"\n" + "Quantity" + qt +"\n"+ "Issued Date" + date;


        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(details, BarcodeFormat.QR_CODE,350,350);

            BarcodeEncoder encoder = new BarcodeEncoder();

            Bitmap bitmap = encoder.createBitmap(matrix);
            ivOutput.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}