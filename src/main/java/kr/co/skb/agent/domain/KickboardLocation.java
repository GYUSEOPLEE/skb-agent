package kr.co.skb.agent.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @NotBlank private String no = "KB202205050001";
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @NotNull private LocalDateTime dateTime;
    @NotNull private double latitude;
    @NotNull private double longitude;
}