package kr.ac.jbnu.se.mobile.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

//toss
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

public class TossActivity extends AppCompatActivity implements Runnable{

    private static String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toss);

        Thread t = new Thread(new TossActivity());
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }

    @Override
    public void run() {
        toss();
    }

    private void toss() {
        //toss
        Client client = ClientBuilder.newClient();
        Entity payload = Entity.json("{  \"apiKey\": \"dcf102a946024eafb1c3d61cbdba3c47\",  \"bankName\": \"기업\",  \"bankAccountNo\": \"21604828802016\",  \"amount\": 15000,  \"message\": \"토스입금버튼\"}");
        Invocation.Builder test = client.target("https://toss.im/transfer-web/linkgen-api/link")
                .request(MediaType.APPLICATION_JSON_TYPE);
        Response response = test.post(payload);

        if(response.getStatus() == 200){
            Log.e("test", "status: " + response.getStatus());
            Log.e("test", "headers: " + response.getHeaders());

            String entityString = response.readEntity(String.class);
            entityString = entityString.split("link\":\"")[1];
            entityString = entityString.split("\"")[0];

            link = entityString;
            Log.e("test", "link: " + link);
        }
        else{
            Log.e("test", "Failed");
        }
    }
}