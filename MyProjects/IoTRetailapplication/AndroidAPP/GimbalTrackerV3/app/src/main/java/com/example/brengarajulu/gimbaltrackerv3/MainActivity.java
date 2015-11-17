package com.example.brengarajulu.gimbaltrackerv3;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.gimbal.android.Gimbal;
import com.gimbal.android.PlaceManager;
import com.gimbal.android.PlaceEventListener;
import com.gimbal.android.Place;
import com.gimbal.android.Visit;
import android.view.View;

import com.gimbal.android.CommunicationManager;
import com.gimbal.android.CommunicationListener;
import com.gimbal.android.Communication;
import com.gimbal.android.Push;

import java.io.Console;
import java.sql.Date;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gimbal.android.BeaconEventListener;
import com.gimbal.android.BeaconManager;
import com.gimbal.android.BeaconSighting;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.w3c.dom.Text;

import android.view.View.OnClickListener;
import android.content.Intent;

public class MainActivity extends ActionBarActivity {

    private PlaceEventListener placeEventListener;
    private CommunicationListener communicationListener;
    private BeaconEventListener beaconSightingListener;
    private BeaconManager beaconManager;
    private boolean isToasted=false;
    private boolean isPromotionPosted=false;
    ImageButton imgPreference;
    Button btnShare;
    //final String getPromotionsUrl = "http://52.11.168.245:8080/theshop/api/v1/getoffers/beaconid/"+args[0]+"/rss/"+args[1]+"?uid=ryan91";
    final String geoLocationUrl = "http://52.11.168.245:9000/heatmap/creategeoloc";
    final String sendEmailUrl="http://52.8.79.0:8080/theshop/api/v1/sendmail";
    //final String url = "http://52.11.168.245:8080/theshop/api/v1/getoffers/beaconid/"+args[0]+"/rss/"+args[1]+"?uid=ryan91";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gimbal.setApiKey(this.getApplication(), "a83cfaa5-2a28-400e-a3d2-9f7bdfa5c0f1");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        addListenerOnButton();
        addPromotionShareListener();
        // Gimbal.resetApplicationInstanceIdentifier();
        isToasted=false;
        placeEventListener = new PlaceEventListener() {
            @Override
            public void onVisitStart(Visit visit) {
                // This will be invoked when a place is entered. Example below shows a simple log upon enter
                Log.i("Info:", "Enter: " + visit.getPlace().getName() + ", at: " + new Date(visit.getArrivalTimeInMillis()));
                TextView txtplace=(TextView) findViewById(R.id.txtplace);
                txtplace.setText("Welcome to "+visit.getPlace().getName());

                TextView txtVisitTime=(TextView) findViewById(R.id.txtVisitTime);
                txtVisitTime.setText("Visited time : "+new Date(visit.getArrivalTimeInMillis()));
                isToasted=false;
            }

            @Override
            public void onVisitEnd(Visit visit) {
                // This will be invoked when a place is exited. Example below shows a simple log upon exit
                Log.i("Info:", "Exit: " + visit.getPlace().getName() + ", at: " + new Date(visit.getDepartureTimeInMillis()));
                TextView txtCommunication=(TextView) findViewById(R.id.txtCommunication);
                txtCommunication.setText("");

            }
        };
        PlaceManager.getInstance().addListener(placeEventListener);

        communicationListener = new CommunicationListener() {
            @Override
            public Collection<Communication> presentNotificationForCommunications(Collection<Communication> communications, Visit visit) {
                String strCommunication="";
                String strTitle="";
                for (Communication comm : communications) {
                    Log.i("INFO", "Place Communication: " + visit.getPlace().getName() + ", message: " + comm.getTitle());
                    strCommunication+=comm.getTitle()+":"+comm.getDescription();


                }
                //allow Gimbal to show the notification for all communications
                TextView txtCommunication=(TextView) findViewById(R.id.txtCommunication);
                txtCommunication.setText(strCommunication);
                return communications;
            }

            @Override
            public Collection<Communication> presentNotificationForCommunications(Collection<Communication> communications, Push push) {
                for (Communication comm : communications) {
                    Log.i("INFO", "Received a Push Communication with message: " + comm.getTitle());

                    TextView txtCommunication=(TextView) findViewById(R.id.txtCommunication);
                    txtCommunication.setText("You have received the offers.Check out!");
                }
                //allow Gimbal to show the notification for all communications
                return communications;
            }

            @Override
            public void onNotificationClicked(List<Communication> communications) {
                Log.i("INFO", "Notification was clicked on");
            }
        };
        CommunicationManager.getInstance().addListener(communicationListener);

        beaconSightingListener = new BeaconEventListener() {
            @Override
            public void onBeaconSighting(BeaconSighting sighting) {
                Toast toast = Toast.makeText(getApplicationContext(), "OFFER AVAILABLE!", Toast.LENGTH_SHORT);
                toast.cancel();
                Log.i("INFO", sighting.getBeacon().getName().toString());
                TextView txtbeacon=(TextView) findViewById(R.id.txtbeacon);
                txtbeacon.setText("Beacon Tracked: "+ sighting.getBeacon().getName()+" Proximity :"+sighting.getRSSI().toString());

                int absoluteRSSI=Math.abs(sighting.getRSSI());
                if(!isToasted)
                    new HttpRequestTask().doInBackground("RS34A",String.valueOf(absoluteRSSI));


            }
        };
        beaconManager = new BeaconManager();
        beaconManager.addListener(beaconSightingListener);

        PlaceManager.getInstance().startMonitoring();
        beaconManager.startListening();
        CommunicationManager.getInstance().startReceivingCommunications();
    }

    public void addListenerOnButton() {

        imgPreference = (ImageButton) findViewById(R.id.imgPreference);
        imgPreference.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent( MainActivity.this, MyPreferences.class);
                startActivity(i);

            }

        });

    }

    public void addPromotionShareListener() {

        btnShare = (Button) findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                  SharePromotion();
            }

        });

    }


    private void SharePromotion()
    {

      TextView txtPromotion = (TextView) findViewById(R.id.txtCommunication);
      TextView txtEmail = (TextView) findViewById(R.id.txtEmail);

        HttpHeaders requestHeaders = new HttpHeaders();
        ShareablePromotion promotion=new ShareablePromotion();
        promotion.setEmail(txtEmail.getText().toString());
        promotion.setPromotion(txtPromotion.getText().toString());
        //requestHeaders.setContentType(new MediaType("application","json"));
        HttpEntity<ShareablePromotion> requestEntity = new HttpEntity<ShareablePromotion>(promotion, requestHeaders);

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the Jackson and String message converters
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP POST request, marshaling the request to JSON, and the response to a String
        restTemplate.exchange(sendEmailUrl, HttpMethod.POST, requestEntity,null);

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), "Promotion Shared Successfully to"+ txtEmail.getText().toString() , duration);
        toast.show();


    }

   /* private String GetPromotion()
    {
        Promotion promo=new Promotion();
        try {
            final String url = "http://rest-service.guides.spring.io/greeting";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            promo=restTemplate.getForObject(url, Promotion.class);
            Log.i("PROMOTION RECEIVED", promo.getContent().toString());
            isToasted=true;
            //Toast toast = Toast.makeText(MainActivity.this, promo.getContent().toString(), Toast.LENGTH_SHORT);
            //toast.show();
            TextView txtCommunication=(TextView) findViewById(R.id.txtCommunication);
            txtCommunication.setText(promo.getContent().toString());


        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
        return promo.getContent();
    } */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private class HttpRequestTask extends AsyncTask<String, Integer, Promotion> {
        @Override
        protected Promotion doInBackground(String...args) {
            //Promotion promo=new Promotion();
            try {

                final String url = "http://52.8.79.0:8080/theshop/api/v1/getoffers/beaconid/"+args[0]+"/rss/"+args[1]+"?uid=ryan91";
                Log.i("SIGHTING", url.toString());
                RestTemplate restTemplate = new RestTemplate();
                //restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                Promotion[] promArr=restTemplate.getForObject(url, Promotion[].class);

                TextView txtCommunication=(TextView) findViewById(R.id.txtCommunication);
                String offer="";
                for(Promotion promotion:promArr)
                {
                    List<String> offers=promotion.getOffers();
                    for(String strOffer:offers)
                    {
                        Log.i("PROMOTION RECEIVED", strOffer);
                        offer+=strOffer +"\n";
                    }

                }
                txtCommunication.setText(offer);
                Toast toast = Toast.makeText(getApplicationContext(), "OFFER AVAILABLE!", Toast.LENGTH_SHORT);
                //toast.show();
               // postGeoLocationSighting(args[0],args[1]);
                if(!isPromotionPosted) {
                    new HttpRequestTask().postGeoLocationSighting("RS34A", String.valueOf(args[1]));
                }
                isToasted=false;
                isPromotionPosted=true;

                //toast.show();


            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        protected void postGeoLocationSighting(String...args) {

            Promotion promo=new Promotion();
            try {

                PostGeoLocation(args[0],args[1]);

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

        }

    }

    private void PostGeoLocation(String beaconId, String rssValue)
    {

        HttpHeaders requestHeaders = new HttpHeaders();
        HeatMapData mapData=new HeatMapData();
        mapData.setBeaconId(beaconId);
        mapData.setNoOfSightings("1");
        //requestHeaders.setContentType(new MediaType("application","json"));
        HttpEntity<HeatMapData> requestEntity = new HttpEntity<HeatMapData>(mapData, requestHeaders);

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the Jackson and String message converters
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP POST request, marshaling the request to JSON, and the response to a String
        restTemplate.exchange(geoLocationUrl, HttpMethod.POST, requestEntity,null);
        // restTemplate.postForObject(geoLocationUrl, mapData, HeatMapData.class);
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
