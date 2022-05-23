package kr.co.skb.agent.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

public interface SystemPath {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Component
    @ConfigurationProperties("agent-path")
    public class AgentPath implements Serializable {
        private String use;
        private String useFile;
        private String location;
        private String locationFile;
        private String command;
        private String commandFile;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Component
    @ConfigurationProperties("watch-path")
    public class WatchPath implements Serializable {
        private String watch;
        private String save;
    }
}
