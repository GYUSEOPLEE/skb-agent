package kr.co.skb.agent.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class KickboardUse implements Serializable {
    @Builder.Default
    @NotBlank private String no = "KB202204170010";
    @Length(max = 1)
    @NotBlank private String use;
}