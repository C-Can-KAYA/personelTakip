package com.example.personeltakipsistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class izin extends AppCompatActivity {

    TextView txt;
    Button btn;
    TextView txt1;
    Button btn1;
    Calendar c;
    DatePickerDialog dpd;
    private String icerik = "";
    private String url4PersonelListesi = "";
    private WebView webq;
    public String[] parcala;
    public String tasinan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izin);
    txt=(TextView)findViewById(R.id.baslangic_tarihi_text);
        btn=(Button) findViewById(R.id.baslangic_tarihi);
        txt1=(TextView)findViewById(R.id.izin_bitis_txt);
        Button buton=(Button)findViewById(R.id.gdr);
        btn1=(Button) findViewById(R.id.izin_bitis_btn);
        tasinan=getIntent().getExtras().getString("veri");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c=Calendar.getInstance();

                int gun=c.get(Calendar.DAY_OF_MONTH);
                int ay=c.get(Calendar.MONTH);
                int yil=c.get(Calendar.YEAR);
                dpd=new DatePickerDialog(izin.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txt1.setText(dayOfMonth+"/"+(month+1)+"/"+(year));
                    }
                },gun,ay,yil);
                dpd.show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
c=Calendar.getInstance();
int gun=c.get(Calendar.DAY_OF_MONTH);
int ay=c.get(Calendar.MONTH);
int yil=c.get(Calendar.YEAR);
dpd=new DatePickerDialog(izin.this, new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) { txt.setText(dayOfMonth+"/"+(month+1)+"/"+(year)); }},gun,ay,yil);dpd.show(); }});







        webq = findViewById(R.id.webq);
        webq.getSettings().setJavaScriptEnabled(true);
        webq.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cmsg)
            {
                String html = cmsg.message();
                icerik = html;
                return false;
            }
            public void onProgressChanged (WebView view,int newProgress){
                if(newProgress == 100){
                    webq.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                url4PersonelListesi="http://192.168.137.1:8000/ekle?tablo=izin&personel_id="+tasinan+"&izinBaslangic="+txt.getText()+"&izinBitis="+txt1.getText();
                webq.loadUrl(url4PersonelListesi);
                Toast.makeText(izin.this,"İşleminiz Başarıyla Gerçekleşti",Toast.LENGTH_LONG).show();
            }
        });


    }
}
