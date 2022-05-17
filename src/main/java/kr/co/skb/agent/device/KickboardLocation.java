package kr.co.skb.agent.device;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KickboardLocation implements Serializable {
    private String no;
    private LocalDateTime dateTime;
    private double latitude;
    private double longitude;
}