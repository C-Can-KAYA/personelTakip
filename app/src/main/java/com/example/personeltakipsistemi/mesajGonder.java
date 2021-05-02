package com.example.personeltakipsistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class mesajGonder extends AppCompatActivity {
    public String tasinan;
    private String icerik = "";
    private String url4PersonelListesi = "";
    private WebView webVieww;
    private WebView webView12;
    public String[] parcala;
    public String sakli;
    public String tarih;
    public int kontrol;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesaj_gonder);
        tasinan=getIntent().getExtras().getString("veri");
      //  Toast.makeText(mesajGonder.this,tasinan,Toast.LENGTH_LONG).show();
       final EditText ad=(EditText) findViewById(R.id.ad);
       final EditText soyad=(EditText) findViewById(R.id.soyad);
        final EditText konu=(EditText) findViewById(R.id.konu);
        final EditText mesaj=(EditText) findViewById(R.id.mesaj);
        Button gonder=(Button)findViewById(R.id.gonder);
        webVieww = findViewById(R.id.webvieww);
        webVieww.getSettings().setJavaScriptEnabled(true);
        webVieww.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cmsg)
            {
                String html = cmsg.message();
                icerik = html;
                icerik=icerik.replace("{","");
                icerik=icerik.replace("}","");
                icerik=icerik.replace("[","");
                icerik=icerik.replace("]","");
                icerik=icerik.replace("\"","");
                icerik=icerik.replace(":","=");
                parcala = icerik.split(",");
                sakli=String.valueOf(parcala[0].replace("personel_id=",""));
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                Calendar calNow = Calendar.getInstance();
                int yıl = calNow.get(Calendar.YEAR);
                int gun = calNow.get(Calendar.DAY_OF_MONTH);
                int ay = calNow.get(Calendar.MONTH);
                int saat = calNow.get(Calendar.HOUR);
                int dakika = calNow.get(Calendar.MINUTE);
                int saniye = calNow.get(Calendar.SECOND);
                if (ay==12)
                {
                    ay=1;
                }
                else
                {
                    ay+=1;
                }
                tarih=yıl+"."+ay+"."+gun+" "+saat+":"+dakika+":"+saniye;
                url4PersonelListesi="http://192.168.137.1:8000/ekle?tablo=mesaj&alici_id="+sakli+"&gonderen_id="+tasinan+"&mesaj="+mesaj.getText()+"&konu="+konu.getText()+"&tarih=12.13.2019";
                webView12.loadUrl(url4PersonelListesi);
                if (kontrol==1)
                {
                    Toast.makeText(mesajGonder.this,"Mesaj gönderildi",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(mesajGonder.this,"Mesaj gönderilemedi",Toast.LENGTH_LONG).show();

                }
           //     Toast.makeText(mesajGonder.this,sakli,Toast.LENGTH_LONG).show();
                return false;
            }
            public void onProgressChanged (WebView view,int newProgress){
                if(newProgress == 100){
                    webVieww.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });
        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                url4PersonelListesi="http://192.168.137.1:8000/listeleAdSoyad?tablo=personelbilgi&adi="+ad.getText()+"&soyadi="+soyad.getText();
                webVieww.loadUrl(url4PersonelListesi);
            }
        });














        webView12 = findViewById(R.id.webview12);
        webView12.getSettings().setJavaScriptEnabled(true);
        webView12.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cmsg)
            {
                String html = cmsg.message();
                icerik = html;
                icerik=icerik.replace("{","");
                icerik=icerik.replace("}","");
                icerik=icerik.replace("[","");
                icerik=icerik.replace("]","");
                icerik=icerik.replace("\"","");
                icerik=icerik.replace(":","=");
                parcala = icerik.split(",");
                if (parcala.equals(""))
                {
                    kontrol=1;
                }
                  else
                {
                    kontrol=0;
                }
                return false;
            }
            public void onProgressChanged (WebView view,int newProgress){
                if(newProgress == 100){
                    webView12.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });


    }
}
