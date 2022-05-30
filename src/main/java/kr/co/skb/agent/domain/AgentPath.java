package kr.co.skb.agent.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

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
}