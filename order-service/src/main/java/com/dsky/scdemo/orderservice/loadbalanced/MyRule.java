package com.dsky.scdemo.orderservice.loadbalanced;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

/**
 * @author colby
 * @date 2020/2/29 20:00
 */
@Slf4j
public class MyRule implements IRule {

    private ILoadBalancer lb;

    private Random random = new Random();

    @Override
    public Server choose(Object key) {
        List<Server> servers = lb.getAllServers();
        Server server = servers.get(random.nextInt(servers.size()));
        log.info("发到 {}", server.getHostPort());
        return server;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.lb = lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return lb;
    }
}
