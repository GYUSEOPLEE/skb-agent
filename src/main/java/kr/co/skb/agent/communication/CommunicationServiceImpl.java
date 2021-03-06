package kr.co.skb.agent.communication;

import kr.co.skb.agent.domain.Kickboard;
import kr.co.skb.agent.domain.KickboardLocation;
import kr.co.skb.agent.domain.KickboardUse;
import kr.co.skb.agent.util.CommunicationUtil;
import kr.co.skb.agent.util.KickboardUtil;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CommunicationServiceImpl implements CommunicationService {
    private final KickboardUtil kickboardUtil;
    private final CommunicationUtil communicationUtil;
    private final KickboardUse kickboardUse;

    @Override
    public void sendKickboard() {
        final Kickboard kickboard = kickboardUtil.getKickboard();

        try {
            communicationUtil.request(kickboard);
        } catch (Exception e) {
            log.error("킥보드 정보 송신 실패");
            log.error(e.getMessage());
        }
    }

    @Override
    public void sendKickboardUse(String use) {
        kickboardUse.setUse(use);

        try {
            communicationUtil.request(kickboardUse);
        } catch (Exception e) {
            log.error("킥보드 사용 정보 송신 실패");
            log.error(e.getMessage());
        }
    }

    @Override
    public void sendKickboardLocation(KickboardLocation kickboardLocation) {
        try {
            communicationUtil.request(kickboardLocation);
        } catch (Exception e) {
            log.error("킥보드 위치 정보 송신 실패");
            log.error(e.getMessage());
        }
    }
}
