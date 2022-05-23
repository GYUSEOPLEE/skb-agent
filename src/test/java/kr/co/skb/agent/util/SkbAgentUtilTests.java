package kr.co.skb.agent.util;

import kr.co.skb.agent.communication.CommunicationService;
import kr.co.skb.agent.domain.Kickboard;
import kr.co.skb.agent.domain.RequestInfo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
class SkbAgentUtilTests {
    @Autowired Kickboard kickboard;
    @Autowired RequestInfo.Ip ip;
    @Autowired RequestInfo.Url url;
    @Autowired CommunicationService communicationService;

    @Test
    public void sendKickboard() throws Exception {
        try {
            communicationService.sendKickboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendKickboardUse() throws Exception {
        try {
            communicationService.sendKickboardUse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendKickboardLocation() throws Exception {
        try {
            communicationService.sendKickboardLocation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
