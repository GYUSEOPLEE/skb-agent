package kr.co.skb.agent.device;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class KickboardLocation implements Serializable {
    private String no;
    private LocalDateTime dateTime;
    private double latitude;
    private double longitude;
}
