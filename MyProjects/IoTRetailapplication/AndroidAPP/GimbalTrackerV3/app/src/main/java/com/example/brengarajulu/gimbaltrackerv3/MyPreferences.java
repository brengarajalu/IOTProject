package com.example.brengarajulu.gimbaltrackerv3;

import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.view.View;

import java.io.Console;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.*;
import android.widget.Toast;


public class MyPreferences extends ActionBarActivity {

    Button btnSave;
    Button btnHome;
    static String updatePreferenceurl="http://52.8.99.113:8080/theshop/api/v1/updateusers/harry01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_preferences);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        addListenerOnButton();
    }
    public void addListenerOnButton() {

        btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                User objUser=new User();
                List<String> disinterests=new ArrayList<String>();

                EditText txtEditable=(EditText)findViewById(R.id.txtCustomExclusion);


                CheckBox chkAlcohol=(CheckBox)findViewById(R.id.chkAlcohol);
                if(chkAlcohol.isChecked())
                {
                    disinterests.add("Alcohol");
                }

                CheckBox chkMeat=(CheckBox)findViewById(R.id.chkMeat);
                if(chkMeat.isChecked())
                {
                    disinterests.add("Meat");
                }

                CheckBox chkTobacco=(CheckBox)findViewById(R.id.chkTobacco);
                if(chkTobacco.isChecked())
                {
                    disinterests.add("Tobacco");
                }
               if(txtEditable.getText().length() > 0)
               disinterests.add(txtEditable.getText().toString());

               objUser.setDisinterests(disinterests);

               objUser.setUserName("Peter");

                // Set the Content-Type header
                HttpHeaders requestHeaders = new HttpHeaders();
                //requestHeaders.setContentType(new MediaType("application","json"));
                HttpEntity<User> requestEntity = new HttpEntity<User>(objUser, requestHeaders);

                // Create a new RestTemplate instance
                RestTemplate restTemplate = new RestTemplate();

                // Add the Jackson and String message converters
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

                // Make the HTTP POST request, marshaling the request to JSON, and the response to a String
                 restTemplate.exchange(updatePreferenceurl, HttpMethod.PUT, requestEntity,null);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), "Preferences successfully updated !", duration);
                toast.show();
                //String result = responseEntity.getBody();


            }

        });

        btnHome = (Button) findViewById(R.id.btnHome);

        btnHome.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent( MyPreferences.this, MainActivity.class);
                startActivity(i);

            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_preferences, menu);
        return true;
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
