package com.dsky.scdemo.orderservice.feign;

import feign.Param;
import feign.RequestLine;

import java.util.List;

/**
 * @author colby
 * @date 2020/3/1 16:53
 */
public interface GitHub {

    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
}
