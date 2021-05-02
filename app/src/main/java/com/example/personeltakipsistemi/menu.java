package com.example.personeltakipsistemi;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;
public class menu extends AppCompatActivity {
    private String url = "file:///android_asset/index.html";
    private WebView webView;
    public double lon;
    public double lat;
    public String lonlat;
    private String icerik = "";
    private String url4PersonelListesi = "";
    private WebView webView1;
    public String[] parcala;
    public int k;
    public String tasinan;
    public String tarih;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final Button btn;
        tasinan=getIntent().getExtras().getString("veri");
        String duzen[]=tasinan.split(",");
        String yetki=duzen[3].replace("yetki=","");
        int yetkii=Integer.valueOf(yetki);
        String k_id=duzen[0].replace("personel_id=","");
        tasinan=k_id;
        final int k=Integer.valueOf(k_id);
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
        Button buton = (Button) findViewById(R.id.izin);
        Button bton = (Button) findViewById(R.id.mesaj);
        bton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intocan = new Intent(menu.this, mesajAraMenu.class);
                intocan.putExtra("veri", tasinan);
                startActivity(intocan);
            }
        });
       Button butn = (Button) findViewById(R.id.cikis);
        butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
       Button uton = (Button) findViewById(R.id.personel);
        uton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(menu.this, personelAraMenu.class);
                intocan.putExtra("veri", tasinan);
                startActivity(intocan);
            }
        });
        Button buon = (Button) findViewById(R.id.ayarlar);
        buon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(menu.this, ayarlar.class);
                intocan.putExtra("veri", tasinan);
                startActivity(intocan);
            }
        });
        if (yetkii==0)
        {
            uton.setVisibility(View.INVISIBLE);
            buton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    Intent intocan = new Intent(menu.this, izin.class);
                    intocan.putExtra("veri", tasinan);
                    startActivity(intocan);
                }
            });
        }
        if (yetkii==1)
        {
            uton.setVisibility(View.VISIBLE);
            buton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intocan = new Intent(menu.this, izinKontrol.class);
                    startActivity(intocan);
                }
            });
        }
        webView1 = findViewById(R.id.webview1);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.setWebChromeClient(new WebChromeClient() {
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
                return false;
            }
            public void onProgressChanged (WebView view,int newProgress){
                if(newProgress == 100){
                    webView1.loadUrl(
                            "javascript:(function() { " +
                                    "var str = document.body.innerText;"+
                                    "console.log(str);" +
                                    "})()");
                }
            }
        });

        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(final String origin, final GeolocationPermissions.Callback callback) {
                final boolean remember = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(menu.this);
                builder.setTitle("Konum");
                builder.setMessage("Konumu paylaşmak ister misin?")
                        .setCancelable(true).setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // origin, allow, remember
                 //       Toast.makeText(menu.this,lonlat,Toast.LENGTH_LONG).show();

                        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                        callback.invoke(origin, true, remember);
                    }
                }).setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // origin, allow, remember
                        callback.invoke(origin, false, remember);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
            public boolean onConsoleMessage(ConsoleMessage cmsg)
            {
                // check secret prefix
                if (cmsg.message().startsWith("MAGIC"))
                {
                    String html = cmsg.message().substring(5);
                    html.replace(" ",",");
                    String[] parcala = html.split(" ");
                    lon=Double.valueOf(parcala[1]);
                    lat=Double.valueOf(parcala[3]);
                    lonlat=lon+","+lat;
           //         Toast.makeText(menu.this,lonlat,Toast.LENGTH_LONG).show();
                    url4PersonelListesi="http://192.168.137.1:8000/ekle?tablo=konum&personel_id="+k+"&konum="+lonlat+"&konum_tarih="+tarih;
                    webView1.loadUrl(url4PersonelListesi);
                    return true;
                }
                return false;
            }
        });
       // webView.getSettings().setGeolocationDatabasePath( menu.this.getFilesDir().getPath() );
        btn = (Button) findViewById(R.id.konum);
        if (yetkii==0)
        {
            btn.setVisibility(View.VISIBLE);
        }
        if (yetkii==1)
        {
            btn.setVisibility(View.INVISIBLE);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(url);

            }
        });
    }
}