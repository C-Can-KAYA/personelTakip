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
import android.widget.TextView;
import android.widget.Toast;

public class personelBilgi extends AppCompatActivity {
    private String icerik = "";
    private String url4PersonelListesi = "";
    private WebView webViewx;
    private WebView webViewxx;
    public String konum="0";
    public String[] parcala;
    public String addd;
    public String soyaddd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel_bilgi);
  final   EditText ad=(EditText)findViewById(R.id.ad);
   final EditText soyad=(EditText)findViewById(R.id.soyad);
       final TextView adi=(TextView) findViewById(R.id.adi);
        final TextView soyadi=(TextView) findViewById(R.id.soyadi);
        final TextView maili=(TextView) findViewById(R.id.maili);
        final TextView telefonu=(TextView) findViewById(R.id.telefonu);
        final TextView adresi=(TextView) findViewById(R.id.adresi);
        final TextView id=(TextView) findViewById(R.id.id);
        webViewx = findViewById(R.id.webviewx);
        webViewx.getSettings().setJavaScriptEnabled(true);
        webViewx.setWebChromeClient(new WebChromeClient() {
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
                if (parcala.length>1)
                {

                    id.setText(parcala[0].replace("personel_id=",""));
                    adi.setText(parcala[1].replace("adi=",""));
                    soyadi.setText(parcala[2].replace("soyadi=",""));
                    telefonu.setText(parcala[3].replace("telefonNo=",""));
                    maili.setText(parcala[4].replace("mailAdresi=",""));
                    adresi.setText(parcala[5].replace("adresi=",""));
                    url4PersonelListesi="http://192.168.137.1:8000/listeleDetay?tablo=konum&personel_id="+id.getText();
                    webViewxx.loadUrl(url4PersonelListesi);
                    addd=String.valueOf(adi.getText());
                    soyaddd=String.valueOf(soyadi.getText());



                }
                return false;
            }
            public void onProgressChanged (WebView view, int newProgress){
                if(newProgress == 100){
                    webViewx.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });

        Button ara = (Button) findViewById(R.id.ara);
        ara.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                url4PersonelListesi="http://192.168.137.1:8000/listeleAdSoyad?tablo=personelbilgi&adi="+ad.getText()+"&soyadi="+soyad.getText();
                webViewx.loadUrl(url4PersonelListesi);


            }
        });







        webViewxx = findViewById(R.id.webviewxx);
        webViewxx.getSettings().setJavaScriptEnabled(true);
        webViewxx.setWebChromeClient(new WebChromeClient() {
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

                if (parcala.length>1)
                {
                    konum=String.valueOf(parcala[(parcala.length)-3]+","+parcala[(parcala.length)-2]);
                    konum=konum.replace("konum=","");

                   konum=konum+","+addd+" "+soyaddd;
                 //   Toast.makeText(personelBilgi.this,konum,Toast.LENGTH_LONG).show();
                }
                return false;
            }
            public void onProgressChanged (WebView view,int newProgress){
                if(newProgress == 100){
                    webViewxx.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });









       Button buton = (Button) findViewById(R.id.konum);
        buton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (konum.equals("0"))
                {
                    Toast.makeText(personelBilgi.this,"Konum Alınamadı",Toast.LENGTH_LONG).show();
                }
                else {
                Intent intocan = new Intent(personelBilgi.this, Harita.class);
                intocan.putExtra("veri", konum);
                startActivity(intocan);
                }
            }
        });
    }
}
