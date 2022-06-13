package kr.co.skb.agent.util;

import kr.co.skb.agent.domain.Kickboard;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class KickboardUtil {
    @Autowired private Kickboard kickboard;

    public Kickboard getKickboard() {
        log.info(this.kickboard + "\n");

        return this.kickboard;
    }
}