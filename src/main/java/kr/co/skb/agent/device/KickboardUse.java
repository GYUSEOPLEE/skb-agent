package kr.co.skb.agent.device;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class KickboardUse implements Serializable {
    private String no;
    private char use;
}
