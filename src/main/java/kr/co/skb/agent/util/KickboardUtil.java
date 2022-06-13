package kr.co.skb.agent.util;

import kr.co.skb.agent.domain.Kickboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class KickboardUtil {
    @Autowired private Kickboard kickboard;

    public Kickboard getKickboard() {
        log.info(this.kickboard + "\n");

        return this.kickboard;
    }
}