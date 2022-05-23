package kr.co.skb.agent.util;

import kr.co.skb.agent.communication.CommunicationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class InitializingDevice implements InitializingBean {
    @Autowired
    private CommunicationService communicationService;

    @Override
    public void afterPropertiesSet() throws Exception {
        for (int i = 0; i < 5; i++) {
            try {
                communicationService.sendKickboard();
                break;
            } catch (Exception e) {
                log.error("킥보드 정보 송신 실패  " + i + "/" + 5);
                Thread.sleep(1000);
            }
        }
    }
}