package com.example.personeltakipsistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mesajAraMenu extends AppCompatActivity {
public String tasinan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesaj_ara_menu);
        Button btn;
        tasinan=getIntent().getExtras().getString("veri");
        btn=(Button)findViewById(R.id.mesajGonder);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(mesajAraMenu.this, mesajGonder.class);
                intocan.putExtra("veri", tasinan);
                startActivity(intocan);
            }
        });
        btn=(Button)findViewById(R.id.mesajoku);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(mesajAraMenu.this, mesajOku.class);
                intocan.putExtra("veri", tasinan);
                startActivity(intocan);
            }
        });
    }
}
