package com.example.personeltakipsistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ayarlar extends AppCompatActivity {
public String tasinan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlar);
        tasinan=getIntent().getExtras().getString("veri");
        Button btn;
        btn=(Button)findViewById(R.id.degis);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(ayarlar.this, personelGuncelle.class);
                intocan.putExtra("veri", tasinan);
                startActivity(intocan);
            }
        });
        btn=(Button)findViewById(R.id.sifre);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(ayarlar.this, sifre.class);
                intocan.putExtra("veri", tasinan);
                startActivity(intocan);
            }
        });
    Button gncl=(Button)findViewById(R.id.guncel);
    }
}
