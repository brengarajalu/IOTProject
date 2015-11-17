package hello;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by aditi on 17/04/15.
 */
public class HttpRequestor {

        public String sendGet(String url) throws Exception {

           // String url = "https://manager.gimbal.com/api/v2/places";

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);

            // add header
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Host","manager.gimbal.com");
            request.setHeader("Authorization", "Token token=6e0b4b8cd815621b18c24a7872bc4ba6");
            request.setHeader("Cache-Control", "no-cache");

            System.out.println("\nSending 'GET' request to URL : " + url);
            HttpResponse response = client.execute(request);
            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            System.out.println("Response Received : "+ result.toString());
            return result.toString();
        }
}
