package kr.co.skb.agent.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KickboardUse implements Serializable {
    @NotBlank private String no;
    @Length(max = 1)
    @NotBlank private String use;
}