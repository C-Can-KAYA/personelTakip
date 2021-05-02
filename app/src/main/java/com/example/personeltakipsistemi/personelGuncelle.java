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

public class personelGuncelle extends AppCompatActivity {
public String tasinan;
    private String icerik = "";
    private String url4PersonelListesi = "";
    private WebView webV;
    public String adi;
    public String soyadi;
    public String tel;
    public String maili;
    public String adresi;
    private String url4PersonelListesi1 = "";
    private WebView webVi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personel_guncelle);
        final   EditText ad = (EditText) findViewById(R.id.adi);
        final  EditText soyad = (EditText) findViewById(R.id.soyadii);
        final  EditText mail = (EditText) findViewById(R.id.mail);
        final  EditText telefon = (EditText) findViewById(R.id.telefon);
        final  EditText adres = (EditText) findViewById(R.id.adres);
        tasinan=getIntent().getExtras().getString("veri");
       webV = findViewById(R.id.webv);
        webV.getSettings().setJavaScriptEnabled(true);
        webV.setWebChromeClient(new WebChromeClient() {
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
                final String parcala[]=icerik.split(",");
                ad.setText(parcala[1].replace("adi=",""));
                soyad.setText(parcala[2].replace("soyadi=",""));
                mail.setText(parcala[4].replace("mailAdresi=",""));
                telefon.setText(parcala[3].replace("telefonNo=",""));
                adres.setText(parcala[5].replace("adresi=",""));
                adi=String.valueOf(ad.getText());
                soyadi=String.valueOf(soyad.getText());
                maili=String.valueOf(mail.getText());
                tel=String.valueOf(telefon.getText());
                adresi=String.valueOf(adres.getText());
                return false;
            }
            public void onProgressChanged (WebView view,int newProgress){
                if(newProgress == 100){
                    webV.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });
      url4PersonelListesi="http://192.168.137.1:8000/listeleDetay?tablo=personelbilgi&personel_id="+tasinan;
        webV.loadUrl(url4PersonelListesi);



        webVi = findViewById(R.id.webvi);
        webVi.getSettings().setJavaScriptEnabled(true);
        webVi.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cmsg)
            {
                String html = cmsg.message();
                return false;
            }
            public void onProgressChanged (WebView view,int newProgress){
                if(newProgress == 100){
                    webVi.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });

        Button btn=(Button)findViewById(R.id.guncel);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                adi=String.valueOf(ad.getText());
                soyadi=String.valueOf(soyad.getText());
                maili=String.valueOf(mail.getText());
                tel=String.valueOf(telefon.getText());
                adresi=String.valueOf(adres.getText());
             url4PersonelListesi1="http://192.168.137.1:8000/guncelle?tablo=personelbilgi&adi="+adi+"&soyadi="+soyadi+"&telefonNo="+tel+"&mailAdresi="+maili+"&adresi="+adresi+"&personel_id="+tasinan+";";
             webVi.loadUrl(url4PersonelListesi1);
                Toast.makeText(personelGuncelle.this,"İşleminiz başarıyla gerçekleşti",Toast.LENGTH_LONG).show();
                Intent intocan = new Intent(personelGuncelle.this, ayarlar.class);
                intocan.putExtra("veri", tasinan);
                startActivity(intocan);
            }
        });



    }
}
