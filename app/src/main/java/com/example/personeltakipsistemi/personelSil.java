package com.example.personeltakipsistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class personelSil extends AppCompatActivity {
public String tasinan="";
    private String icerik = "";
    private String url4PersonelListesi = "";
    private WebView webViewz;
    private WebView webViewq;
    private WebView webViewc;
    public String[] parcala;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel_sil);
        tasinan=getIntent().getExtras().getString("veri");
        Button btn=(Button)findViewById(R.id.sil);
        final EditText tel=(EditText)findViewById(R.id.tell);
        EditText soyad=(EditText)findViewById(R.id.soyadd);
        EditText ad=(EditText)findViewById(R.id.adii);
        webViewz = findViewById(R.id.webviewz);
        webViewz.getSettings().setJavaScriptEnabled(true);
        webViewz.setWebChromeClient(new WebChromeClient() {
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
                if (parcala.length<1)
                {
Toast.makeText(personelSil.this,"Aradığınız kişi bulunamadı",Toast.LENGTH_LONG).show();
                }
                else
                {
                    String saklı=parcala[0];
                    saklı=saklı.replace("personel_id=","");
                    url4PersonelListesi="http://192.168.137.1:8000/sil?tablo=personelbilgi&telefonNo="+tel.getText();
                    webViewc.loadUrl(url4PersonelListesi);
                    url4PersonelListesi="http://192.168.137.1:8000/sil?tablo=personel&personel_id="+saklı;
                    webViewq.loadUrl(url4PersonelListesi);
                    Toast.makeText(personelSil.this,"İşleminiz Başarıyla gerçekleştirildi",Toast.LENGTH_LONG).show();

                }
                return false;
            }
            public void onProgressChanged (WebView view, int newProgress){
                if(newProgress == 100){
                    webViewz.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });











        webViewc = findViewById(R.id.webviewc);
        webViewc.getSettings().setJavaScriptEnabled(true);
        webViewc.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cmsg)
            {
                String html = cmsg.message();
                icerik = html;
                return false;
            }
            public void onProgressChanged (WebView view,int newProgress){
                if(newProgress == 100){
                    webViewc.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });









        webViewq = findViewById(R.id.webviewq);
        webViewq.getSettings().setJavaScriptEnabled(true);
        webViewq.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cmsg)
            {
                String html = cmsg.message();
                icerik = html;
                return false;
            }
            public void onProgressChanged (WebView view,int newProgress){
                if(newProgress == 100){
                    webViewq.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });











        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                url4PersonelListesi="http://192.168.137.1:8000/listeleTelefon?tablo=personelbilgi&telefonNo="+tel.getText();
                webViewz.loadUrl(url4PersonelListesi);


            }
        });
    }
}
