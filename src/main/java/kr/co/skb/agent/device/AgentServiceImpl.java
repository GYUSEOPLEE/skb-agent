package kr.co.skb.agent.device;

import kr.co.skb.agent.domain.KickboardLocation;
import kr.co.skb.agent.domain.KickboardUse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Scope("prototype")
public class AgentServiceImpl implements AgentService, Runnable {
    @Override
    public KickboardUse checkKickboardUse() {
        // TODO: 센서 확인
        return new KickboardUse("KB202205050001", "Y");
    }

    @Override
    public KickboardLocation checkKickboardLocation() {
        return null;
    }

    @Override
    public void run() {

    }
}
