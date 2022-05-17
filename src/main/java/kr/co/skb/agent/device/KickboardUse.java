package kr.co.skb.agent.device;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KickboardUse implements Serializable {
    private String no;
    private String use;
}