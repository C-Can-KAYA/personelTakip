package com.example.personeltakipsistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class personelAraMenu extends AppCompatActivity {
public String tasinan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel_ara_menu);
        Button btn;
        tasinan=getIntent().getExtras().getString("veri");
     //   Toast.makeText(personelAraMenu.this,tasinan,Toast.LENGTH_LONG).show();
        btn=(Button)findViewById(R.id.personelEkleSil);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(personelAraMenu.this, personelEkleSilAraMenu.class);
                intocan.putExtra("veri", tasinan);
                startActivity(intocan);
            }
        });
        btn=(Button)findViewById(R.id.personelBilgi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(personelAraMenu.this, personelBilgi.class);
                intocan.putExtra("veri", tasinan);
                startActivity(intocan);
            }
        });
    }
}
