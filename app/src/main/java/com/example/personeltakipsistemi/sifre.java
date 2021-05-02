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

public class sifre extends AppCompatActivity {
    private String url4PersonelListesi = "";
    private WebView webViewn;
    public String tasinan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre);
        tasinan=getIntent().getExtras().getString("veri");
        webViewn = findViewById(R.id.webviewn);
        webViewn.getSettings().setJavaScriptEnabled(true);
        webViewn.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cmsg)
            {
                String html = cmsg.message();
                return false;
            }
            public void onProgressChanged (WebView view,int newProgress){
                if(newProgress == 100){
                    webViewn.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });
        Button gnclle=(Button)findViewById(R.id.guncelle);
        final EditText sifre=(EditText)findViewById(R.id.sifre);
        final EditText sifreTekrar=(EditText) findViewById(R.id.sifreTekrar);


        gnclle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String kontrol=String.valueOf(sifre.getText());
                String kontrol1=String.valueOf(sifreTekrar.getText());
                if (kontrol.equals(kontrol1))
                {
                      url4PersonelListesi="http://192.168.137.1:8000/yeni_sifre?tablo=personel&sifre="+sifre.getText()+"&personel_id="+tasinan;
                        webViewn.loadUrl(url4PersonelListesi);
                Toast.makeText(sifre.this,"İşleminiz başarıyla gerçekleşti",Toast.LENGTH_LONG).show();
               Intent intocan = new Intent(sifre.this, ayarlar.class);
                intocan.putExtra("veri", tasinan);
                startActivity(intocan);
                }
                else
                {
                    Toast.makeText(sifre.this,"şifreler uyuşmuyor",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
