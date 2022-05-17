package kr.co.skb.agent.util;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Kickboard implements Serializable {
    private String no;
    private String model;
    private String ip;
}