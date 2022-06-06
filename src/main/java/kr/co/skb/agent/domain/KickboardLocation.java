package kr.co.skb.agent.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class KickboardLocation implements Serializable {
    @Builder.Default
    @NotBlank private String kickboardNo = "KB202205050001";
    @NotNull private LocalDateTime dateTime;
    @NotNull private double latitude;
    @NotNull private double longitude;
}