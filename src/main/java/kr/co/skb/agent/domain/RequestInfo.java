package kr.co.skb.agent.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

public interface RequestInfo {
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Component
    @ConfigurationProperties("ip")
    public class Ip {
        private String system;
        private String helmet;
    }

    @Getter @Setter
    @Component
    @ConfigurationProperties("url")
    public class Url {
        private String info;
        private String use;
        private String location;
    }
}
