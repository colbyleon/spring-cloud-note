package com.dsky.scdemo.orderservice.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 代表一个使用 hystrix 执行的命令
 *
 * @author colby.luo
 * @date 2020/3/2 9:39
 */
public class MyHystrixCommand extends HystrixCommand<String> {
    private final String name;

    public MyHystrixCommand(String name) {
        super(HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                )
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)
                        .withMaxQueueSize(100)
                        .withMaximumSize(100)
                )
        );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
//        TimeUnit.SECONDS.sleep(10);
        System.err.println("refresh data");
        return this.name + ":" + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return "失败了";
    }


    /**
     * 通过重写getCacheKey来判断是否返回缓存的数据，getCacheKey可以根据参数来生成。这样，同样的参数就可以都用到缓存了。
     */
    @Override
    protected String getCacheKey() {
        return String.valueOf(this.name);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<String> future = new MyHystrixCommand("cool").queue();
        System.out.println(future.get());
        String result = new MyHystrixCommand("cool").execute();
        System.out.println(result);
        context.shutdown();
    }
}
