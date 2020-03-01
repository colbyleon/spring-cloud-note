package com.dsky.scdemo.orderservice.feign;

import com.fasterxml.jackson.databind.json.JsonMapper;
import feign.Feign;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author colby
 * @date 2020/3/1 16:52
 */
public class NoMvcFeignTest {
    public static void main(String[] args) {
        GitHub gitHub = Feign.builder()
                .decoder(new JsonDecoder())
                .target(GitHub.class, "https://api.github.com");

        List<Contributor> contributors = gitHub.contributors("OpenFeign", "feign");
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }
    }


    static class JsonDecoder implements Decoder {

        @Override
        public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
            JsonMapper jm = JsonMapper.builder().build();
            if (response.status() == 200) {
                InputStream in = response.body().asInputStream();
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    jm.getTypeFactory().constructParametricType(
                            (Class<?>) parameterizedType.getRawType(),
                            (Class<?>[]) parameterizedType.getActualTypeArguments());
                } else if (type instanceof Class) {
                    return jm.readValue(in, (Class<?>) type);
                }
            }
            return null;
        }
    }
}
