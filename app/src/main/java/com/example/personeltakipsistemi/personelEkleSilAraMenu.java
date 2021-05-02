package com.example.personeltakipsistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class personelEkleSilAraMenu extends AppCompatActivity {
    public String tasinan;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasinan=getIntent().getExtras().getString("veri");
        setContentView(R.layout.activity_personel_ekle_sil_ara_menu);
        Button btn;
        btn=(Button)findViewById(R.id.ekle);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(personelEkleSilAraMenu.this, personelEkle.class);
                intocan.putExtra("veri", tasinan);
                startActivity(intocan);
            }
        });
        btn=(Button)findViewById(R.id.sil);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(personelEkleSilAraMenu.this, personelSil.class);
                intocan.putExtra("veri", tasinan);
                startActivity(intocan);

            }
        });

    }
}
