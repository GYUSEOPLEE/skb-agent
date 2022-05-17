package kr.co.skb.agent.communication;

import kr.co.skb.agent.device.AgentService;
import kr.co.skb.agent.device.KickboardLocation;
import kr.co.skb.agent.device.KickboardUse;
import kr.co.skb.agent.util.CommunicationUtil;
import kr.co.skb.agent.util.Kickboard;
import kr.co.skb.agent.util.KickboardUtil;
import kr.co.skb.agent.util.LocationValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Log4j2
@Service
public class CommunicationServiceImpl implements CommunicationService {
    @Autowired
    private KickboardUtil kickboardUtil;
    @Autowired
    private CommunicationUtil communicationUtil;
    @Autowired
    private AgentService agentService;

    private String kickboardUse = "N";

    @Override
    public void sendKickboard() throws Exception {
        final Kickboard kickboard = kickboardUtil.getKickboard();

        if (kickboardUtil.isEmpty(kickboard)) {
            log.debug("킥보드 정보 조회 실패");
            return;
        }

        try {
            communicationUtil.request(kickboard);
        } catch (Exception e) {
            log.error("킥보드 정보 송신 실패");
            log.error(e.getStackTrace());
        }
    }

    @Override
    public void sendKickboardUse() throws Exception {
        final KickboardUse kickboardUse = agentService.checkKickboardUse();
        if (!this.kickboardUse.equalsIgnoreCase(kickboardUse.getUse())) {
            log.debug("킥보드 사용 정보가 변경되지 않음");

            return;
        }

        this.kickboardUse = kickboardUse.getUse();

        try {
            communicationUtil.request(kickboardUse);
        } catch (Exception e) {
            log.error("킥보드 사용 정보 송신 실패");
            log.error(e.getStackTrace());
        }
    }

    @Override
    public void sendKickboardLocation() throws Exception {
        LocalDateTime dateTime = LocalDateTime.now();

        KickboardLocation kickboardLocation = agentService.checkKickboardLocation();
        LocationValidator locationValidator = new LocationValidator();
        kickboardLocation = locationValidator.validateLocation(kickboardLocation);

        try {
            kickboardLocation.setDateTime(dateTime);
            communicationUtil.request(kickboardLocation);
        } catch (Exception e) {
            log.error("킥보드 위치 정보 송신 실패");
            log.error(e.getStackTrace());
        }

    }
}
