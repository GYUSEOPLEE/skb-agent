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
    public void test() throws Exception {
        try {
            communicationService.sendKickboardUse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
