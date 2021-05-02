package com.example.personeltakipsistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class mesajOku extends AppCompatActivity {
    private String icerik = "";
    private String url4PersonelListesi = "";
    public WebView web;
    public String[] parcala;
    public String tasinan="1";
    private String[] ulkeler =
            {"1", "Ali", "Kaya", "başlık","mesaj"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesaj_oku);
        tasinan=getIntent().getExtras().getString("veri");
       final ListView liste=(ListView) findViewById(R.id.listview);
        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, ulkeler);
        liste.setAdapter(veriAdaptoru);
        web = findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebChromeClient(new WebChromeClient() {
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
                String [ ] yerlestir = new String[parcala.length];
                int sayac=0;
                while (parcala.length>sayac)
                {
                    yerlestir[sayac]=parcala[0];
                    sayac+=1;
                }


                liste.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        Toast.makeText(getApplicationContext(),ulkeler[position], Toast.LENGTH_LONG).show();

                        AlertDialog.Builder builder = new AlertDialog.Builder(mesajOku.this);
                        builder.setTitle("Mesaj Konu");
                        builder.setMessage("mesaj");
                        builder.setPositiveButton("Okundu", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builder.show();
                    }
                });
                return false;
            }
            public void onProgressChanged (WebView view,int newProgress){
                if(newProgress == 100){
                    web.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });
        url4PersonelListesi="http://192.168.137.1:8000/mesaj?tablo=mesaj,personelbilgi&alici_id="+tasinan;
        web.loadUrl(url4PersonelListesi);
    }
}
