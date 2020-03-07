package com.dsky.scdemo.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author colby.luo
 * @date 2020/3/4 16:16
 */
public class IpFilter extends ZuulFilter {
    private List<String> blackIpList = Arrays.asList(
            "127.0.0.1"
            , "192.168.118.110"
            , "0:0:0:0:0:0:0:1"
    );

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String ip = ctx.getRequest().getRemoteAddr();
        if (StringUtils.isNotBlank(ip) && blackIpList.contains(ip)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("非法请求");
            ctx.getResponse().setHeader("Content-Type", "text/plain;Charset=utf8");
        }
        return null;
    }
}
