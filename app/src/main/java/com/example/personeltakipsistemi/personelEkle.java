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
public class personelEkle extends AppCompatActivity {
    private String url4PersonelListesi = "";
    private WebView webView789;
    public String icerik = "";
    private String url4PersonelListesi1 = "";
    private WebView webView123;
    private WebView webView234;
    public String[] parcala;
    public int boyut=0;
    public String sakli="";
    public String tasinan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel_ekle);
        final EditText ad=(EditText)findViewById(R.id.add);
        final EditText kullanici=(EditText)findViewById(R.id.kullanicii);
        final EditText sifre=(EditText)findViewById(R.id.sifree);
        final EditText soyad=(EditText)findViewById(R.id.soyadd);
        final EditText mail=(EditText)findViewById(R.id.maill);
        final EditText tel=(EditText)findViewById(R.id.tell);
        final EditText adres=(EditText)findViewById(R.id.adress);
        Button ekle=(Button)findViewById(R.id.ekle);
        webView789 = findViewById(R.id.webView789);
        webView789.getSettings().setJavaScriptEnabled(true);
        webView789.setWebChromeClient(new WebChromeClient() {
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
                sakli=parcala[(parcala.length)-6];
                sakli=sakli.replace("personel_id=","");
               int saklii=Integer.valueOf(sakli);
               saklii=saklii+1;
               sakli=String.valueOf(saklii);
                url4PersonelListesi="http://192.168.137.1:8000/ekle?tablo=personelbilgi&personel_id="+sakli+"&adi="+ad.getText()+"&soyadi="+soyad.getText()+"&telefonNo="+tel.getText()+"&mailAdresi="+mail.getText()+"&adresi="+adres.getText();
                webView123.loadUrl(url4PersonelListesi);
                url4PersonelListesi="http://192.168.137.1:8000/ekle?tablo=personel&personel_id="+sakli+"&kullaniciAdi="+kullanici.getText()+"&sifre="+sifre.getText()+"&yetki=0&personel_bilgi="+tel.getText();
            webView234.loadUrl(url4PersonelListesi);
                return false;
            }
            public void onProgressChanged (WebView view,int newProgress){
                if(newProgress == 100){
                    webView789.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });
        webView234 = findViewById(R.id.webview234);
        webView234.getSettings().setJavaScriptEnabled(true);
        webView234.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cmsg)
            {
                String html = cmsg.message();
                icerik = html;
                return false;
            }
            public void onProgressChanged (WebView view,int newProgress){
                if(newProgress == 100){
                    webView234.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });
        webView123 = findViewById(R.id.webview123);
        webView123.getSettings().setJavaScriptEnabled(true);
        webView123.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cmsg)
            {
                String html = cmsg.message();
                icerik = html;
                return false;
            }
            public void onProgressChanged (WebView view,int newProgress){
                if(newProgress == 100){
                    webView123.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                url4PersonelListesi="http://192.168.137.1:8000/listele?tablo=personelbilgi";
                webView789.loadUrl(url4PersonelListesi);
                Toast.makeText(personelEkle.this,"İşleminiz Başarıyla gerçekleşti",Toast.LENGTH_LONG);
                Intent intocan = new Intent(personelEkle.this, personelEkleSilAraMenu.class);
                intocan.putExtra("veri", tasinan);
                startActivity(intocan);
            }
        });

    }
}
