package kr.co.skb.agent.device;

import kr.co.skb.agent.domain.KickboardLocation;
import kr.co.skb.agent.domain.KickboardUse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Log4j2
@Service
public class AgentServiceImpl implements AgentService {
    @Override
    public KickboardUse checkKickboardUse(String use) {
        return KickboardUse.builder()
                .use(use)
                .build();
    }

    @Override
    public KickboardLocation checkKickboardLocation(String[] location) throws NumberFormatException {
        return KickboardLocation.builder()
                .dateTime(LocalDateTime.now())
                .latitude(Double.parseDouble(location[0]))
                .longitude(Double.parseDouble(location[1]))
                .build();
    }
}