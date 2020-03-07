package com.dsky.scdemo.orderservice.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.Future;

/**
 * 使用Hystrix来实现数据缓存功能。有缓存必然就有清除缓存的动作，当数据发生变动时，必须将缓存中的数据也更新掉，不然就会出现脏数据的问题。
 * 同样地，Hystrix也有清除缓存的功能。
 *
 * @author colby.luo
 * @date 2020/3/2 11:17
 */
public class ClearCacheHystrixCommand extends HystrixCommand<String> {

    private final String name;

    private static final HystrixCommandKey CETTER_KEY = HystrixCommandKey.Factory.asKey("MyKey");

    public ClearCacheHystrixCommand(String name) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
                .andCommandKey(CETTER_KEY));
        this.name = name;
    }

    /**
     * 清除缓存
     */
    public static void flushCache(String name) {
        HystrixRequestCache.getInstance(CETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(name);
    }

    @Override
    protected String run() throws Exception {
        System.err.println("refresh data");
        return this.name + ":" + Thread.currentThread().getName();
    }

    @Override
    protected String getCacheKey() {
        return this.name;
    }

    public static void main(String[] args) throws Exception{
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        String result = new ClearCacheHystrixCommand("ooccu").execute();
        System.out.println(result);
        ClearCacheHystrixCommand.flushCache("ooccu");
        Future<String> future = new ClearCacheHystrixCommand("ooccu").queue();
        System.out.println(future.get());
        context.shutdown();
    }
}
