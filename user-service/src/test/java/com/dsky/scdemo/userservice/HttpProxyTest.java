package com.dsky.scdemo.userservice;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Properties;

/**
 * @author colby
 * @date 2020/2/29 12:37
 */
public class HttpProxyTest {

    static {
        Properties props = System.getProperties();
        props.put("http.proxyHost", "127.0.0.1");
        props.put("http.proxyPort", "8888");
    }

    public static void main(String[] args) throws IOException {
        HttpHost httpHost = HttpHost.create("127.0.0.1:8888");
        CloseableHttpClient httpClient = HttpClients.custom()
//                .setProxy(httpHost)
                .build();

        HttpGet httpGet = new HttpGet("http://www.baidu.com");


        CloseableHttpResponse response = httpClient.execute(httpGet);

        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        System.out.println(result);
    }

    @Test
    public void dd() {
        Properties props = System.getProperties();
        props.put("http.proxyHost", "127.0.0.1");
        props.put("http.proxyPort", "8888");

        RestTemplate restTemplate = new RestTemplate();
        String tt = restTemplate.getForObject("http://baike.baidu.com/", String.class);
        System.out.println(tt);
    }
}
