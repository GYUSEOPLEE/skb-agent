package kr.co.skb.agent.communication;

import kr.co.skb.agent.domain.Kickboard;
import kr.co.skb.agent.domain.RequestInfo;
import kr.co.skb.agent.util.CommunicationUtil;
import kr.co.skb.agent.util.KickboardUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
class SkbAgentCommunicationTests {
    @Autowired
    private KickboardUtil kickboardUtil;

    @Autowired
    private CommunicationUtil communicationUtil;

    @Autowired RequestInfo.Ip ip;
    @Autowired RequestInfo.Url url;


    void sendKickboardTest() throws Exception {
        Kickboard kickboard = kickboardUtil.getKickboard();

//        if (kickboardUtil.isEmpty(kickboard)) {
//            log.debug("킥보드 정보 조회 실패");
//            return;
//        }

        try {
            communicationUtil.request(kickboard);
            log.info("킥보드 정보 송신 완료");
        } catch (Exception e) {
            log.debug("킥보드 정보 송신 실패");
        }
    }
}
