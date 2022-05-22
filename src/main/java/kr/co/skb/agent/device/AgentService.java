package kr.co.skb.agent.device;

import kr.co.skb.agent.domain.KickboardLocation;
import kr.co.skb.agent.domain.KickboardUse;

public interface AgentService {
    public KickboardUse checkKickboardUse();
    public KickboardLocation checkKickboardLocation();
}