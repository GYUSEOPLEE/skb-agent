package kr.co.skb.agent.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.*;

import java.io.Serializable;

public interface RequestInfo {
    @Data
    @NoArgsConstructor @AllArgsConstructor
    @Component @ConfigurationProperties("ip")
    public class Ip implements Serializable {
        private String system;
        private String helmet;
    }

    @Data
    @NoArgsConstructor @AllArgsConstructor
    @Component @ConfigurationProperties("url")
    public class Url implements Serializable {
        private String info;
        private String use;
        private String location;
    }
}
