package xyz.qjex.cfutils.methods;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by qjex on 5/26/16.
 */
public class HTTPMethods {

    public static String get(String query) throws IOException {
        HttpClient httpClinet = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(query);
        HttpResponse response = httpClinet.execute(request);
        return IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);

    }
}
