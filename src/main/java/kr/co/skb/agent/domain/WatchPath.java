package kr.co.skb.agent.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@ConfigurationProperties("path")
public class WatchPath implements Serializable {
    private String watch;
    private String save;
}
