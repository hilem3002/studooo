package com.example.studooov2.UserSignUpLogin.Activities.notiticationActivity;

import static android.os.Build.VERSION.SDK_INT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.NotificationAdapter;
import com.example.studooov2.UserSignUpLogin.NotificationRecyclerviewInterface;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class NotificationRecyclerviewActivity extends AppCompatActivity implements NotificationRecyclerviewInterface {

    private RecyclerView notificationRV;

    private NotificationAdapter adapter;


    private ArrayList<String> duyuruBody;

    private ArrayList<String> duyuruMonth;

    private ArrayList<String> duyuruDay;

    private ArrayList<String> duyuruLinks;

    private SearchView searchView;

    private String bolumIsmi;

    private String bolumWebLink;

    private int control;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_recyclerview);

        // Intent ile gelen bolum ismi ve linkini alma.
        bolumIsmi = getIntent().getStringExtra("BOLUMNAME");
        bolumWebLink = getIntent().getStringExtra("BOLUMWEBLİNK");

        // ana methodu çalıştırma
        startNotifications(bolumWebLink);

    }



    @Override
    public void onItemClick(int position) {
        // Recyclerview içerisinde bir elemente tıklama ve web browser'ının açılması
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(duyuruLinks.get(position)));
        startActivity(intent);
    }

    public void startNotifications(String bolumWebLink){
        try {
            control = 0;
            duyuruBody = new ArrayList<>();
            duyuruMonth = new ArrayList<>();
            duyuruDay = new ArrayList<>();
            duyuruLinks = new ArrayList<>();
            // https:zartzurt/ --> https:zartzurt
            StringBuffer bolumWebLinkLastVersion= new StringBuffer(bolumWebLink);
            bolumWebLinkLastVersion.deleteCharAt(bolumWebLinkLastVersion.length()-1);
            if (SDK_INT >= 10) {
                StrictMode.ThreadPolicy tp = StrictMode.ThreadPolicy.LAX;
                StrictMode.setThreadPolicy(tp);
                // Bu olmaz ise web scraping hata veriyor.
            }
            final String url = bolumWebLink;
            Document document = Jsoup.connect(url).get();
            Elements haberBody = document.select("div.media-body");
            Elements haberBaslik = haberBody.select("a.title").select("p");
            Elements haberDate = document.select("div.col-12");
            Elements haberDatee = haberDate.select("p.month");
            Elements haberDays = document.select("div.col-12").select("p.day");
            Elements links = document.select("div.col-12").select("div.media-body").select("a.title");
            for(Element link : links){
                String linkee = link.attr("href");
                if(control %2 == 0){
                    duyuruLinks.add(bolumWebLinkLastVersion + linkee);
                    // Burada web scraping ile alakalı aynı linki 2 kere ekliyor onu düzeltmek için
                    // control değişkeniyle kontrol yaptım.
                }
                control ++;
            }
            for (Element duyuru : haberBaslik) {
                duyuruBody.add(duyuru.text());
            }

            for(Element haberMonth : haberDatee){
                duyuruMonth.add(haberMonth.text());
            }

            for(Element haberDay :  haberDays){
                duyuruDay.add(haberDay.text());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        notificationRV = findViewById(R.id.notificationRV);
        notificationRV.setHasFixedSize(true);
        notificationRV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotificationAdapter(this,this, duyuruBody, duyuruMonth, duyuruDay);
        notificationRV.setAdapter(adapter);
    }
}
