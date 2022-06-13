package kr.co.skb.agent.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data @Component
@NoArgsConstructor @AllArgsConstructor
@ConfigurationProperties("kickboard")
public class Kickboard implements Serializable {
    @NotBlank private String no;
    @NotBlank private String model;
    @NotBlank private String ip;
}