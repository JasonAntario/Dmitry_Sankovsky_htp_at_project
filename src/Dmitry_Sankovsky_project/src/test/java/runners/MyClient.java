package runners;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import utills.Search;

import java.io.IOException;
import java.net.URISyntaxException;


public class MyClient {


    private static String data;

    public static void main(String[] args) throws URISyntaxException, IOException {
        Search search = new Search("",false);
        userSearch(search);
    }

    public void nbrbREquest() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("https://www.nbrb.by/api/exrates/currencies/28");
        HttpGet request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    public static void userSearch(Search search) throws URISyntaxException, IOException {
        Gson gson = new Gson();
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("http://178.124.206.46:8001/app/ws/");
        HttpPost request = new HttpPost(builder.build());
        request.setEntity(new StringEntity(gson.toJson(search)));
        HttpResponse response = client.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));

    }

}