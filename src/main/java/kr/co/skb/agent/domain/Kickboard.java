package kr.co.skb.agent.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@ConfigurationProperties("kickboard")
public class Kickboard implements Serializable {
    private String no;
    private String model;
    private String ip;

    public boolean isEmpty(final Kickboard kickboard) {
        if (kickboard == null) {
            return true;
        } else if (kickboard.getIp().trim().length() < 1
                || kickboard.getModel().trim().length() < 1
                || kickboard.getIp().trim().length() < 1) {
            return true;
        }

        return false;
    }
}