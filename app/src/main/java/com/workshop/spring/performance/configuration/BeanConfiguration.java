package com.workshop.spring.performance.configuration;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class BeanConfiguration {

    @Bean
    public HttpClient defaultHttpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 500)
                .responseTimeout(Duration.ofMillis(500))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(500, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(500, TimeUnit.MILLISECONDS)));
    }

}
