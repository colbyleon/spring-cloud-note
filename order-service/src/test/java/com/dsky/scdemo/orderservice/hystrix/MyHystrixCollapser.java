package com.dsky.scdemo.orderservice.hystrix;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author colby.luo
 * @date 2020/3/3 9:32
 */
public class MyHystrixCollapser extends HystrixCollapser<List<String>, String, String> {
    private String name;

    public MyHystrixCollapser(String name) {
        this.name = name;
    }

    @Override
    public String getRequestArgument() {
        return name;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, String>> requests) {
        return new BatchCommand(requests);
    }

    @Override
    protected void mapResponseToRequests(List<String> batchResponse, Collection<CollapsedRequest<String, String>> requests) {
        int count = 0;
        for (CollapsedRequest<String, String> request : requests) {
            request.setResponse(batchResponse.get(count++));
        }
    }

    private static class BatchCommand extends HystrixCommand<List<String>> {

        private final Collection<CollapsedRequest<String, String>> requests;

        private BatchCommand(Collection<CollapsedRequest<String, String>> requests) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueForKey"))
            );
            this.requests = requests;
        }

        @Override
        protected List<String> run() throws Exception {
            System.out.println("真正执行请求.....");
            ArrayList<String> responses = new ArrayList<>();
            for (CollapsedRequest<String, String> request : requests) {
                responses.add("返回结果： " + request.getArgument());
            }
            return responses;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<String> f1 = new MyHystrixCollapser("zhangsan").queue();
        Future<String> f2 = new MyHystrixCollapser("lisi").queue();
        System.out.println(f1.get());
        System.out.println(f2.get());
        context.shutdown();
    }
}
