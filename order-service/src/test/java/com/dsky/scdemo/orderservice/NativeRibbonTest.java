package com.dsky.scdemo.orderservice;

import com.google.common.collect.Lists;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import com.sun.javafx.scene.transform.TransformUtils;
import org.apache.commons.collections.TransformerUtils;
import rx.Observable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * @author colby
 * @date 2020/2/29 16:22
 */
public class NativeRibbonTest {
    public static void main(String[] args) {
        List<Server> serverList = Lists.newArrayList(new Server("localhost", 8081),
                new Server("localhost", 8083));

        ILoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(serverList);

        for (int i = 0; i < 5; i++) {
            String result = LoadBalancerCommand.<String>builder()
                    .withLoadBalancer(loadBalancer)
                    .build()
                    .submit(new ServerOperation<String>() {
                        @Override
                        public Observable<String> call(Server server) {
                            String addr = String.format("http://%s:%d/user/hello", server.getHost(), server.getPort());
                            System.out.println("调用地址 " + addr);
                            try {
                                URL url = new URL(addr);
                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                conn.setRequestMethod("GET");
                                conn.connect();
                                InputStream in = conn.getInputStream();
                                byte[] bytes = new byte[in.available()];

                                //noinspection ResultOfMethodCallIgnored
                                in.read(bytes);
                                return Observable.just(new String(bytes));
                            } catch (Exception e) {
                                return Observable.error(e);
                            }
                        }
                    })
                    .toBlocking()
                    .first();
            System.out.println(result);
        }
    }
}
