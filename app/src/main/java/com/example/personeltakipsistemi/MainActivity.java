package com.example.personeltakipsistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String icerik = "";
    private String url4PersonelListesi = "";
    private WebView webView;
    public String[] parcala;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    webView = findViewById(R.id.webview);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.setWebChromeClient(new WebChromeClient() {
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
            if (icerik.equals("0") || icerik.equals(""))
            {
                Toast.makeText(MainActivity.this,"hatalı giriş",Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent intocan = new Intent(MainActivity.this, menu.class);
                intocan.putExtra("veri", icerik);
                startActivity(intocan);
            }
return false;
        }
        public void onProgressChanged (WebView view,int newProgress){
            if(newProgress == 100){
                webView.loadUrl(
                        "javascript:(function() { " +
                                "var str = document.body.innerText;"+
                                "console.log(str);" +
                                "})()");
            }
        }
    });
        Button buton;
        final EditText text1=(EditText) findViewById(R.id.sifre);
        final EditText text=(EditText) findViewById(R.id.KullaniciAdi);
        buton=(Button)findViewById(R.id.giris);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                url4PersonelListesi="http://192.168.137.1:8000/kontrol?tablo=personel&kullaniciAdi="+text.getText()+"&sifre="+text1.getText();
                webView.loadUrl(url4PersonelListesi);


  }
        });
    }
}
