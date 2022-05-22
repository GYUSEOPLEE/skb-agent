package kr.co.skb.agent.domain;

import lombok.*;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KickboardUse implements Serializable {
    private String no;
    private String use;
}