package kr.co.skb.agent.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter @Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KickboardUse implements Serializable {
    @Builder.Default
    @NotBlank private String no = "KB202205050001";
    @Length(max = 1)
    @NotBlank private String use;
}