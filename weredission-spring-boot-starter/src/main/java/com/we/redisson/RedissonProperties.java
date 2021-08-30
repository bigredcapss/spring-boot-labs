package com.we.redisson;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author we
 * @date 2021-08-25 14:19
 **/
@ConfigurationProperties(prefix = "we.redisson")
public class RedissonProperties {
    private String host = "localhost";
    private Integer port = 6379;
    private Integer timeout;
    private boolean ssl;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }
}
