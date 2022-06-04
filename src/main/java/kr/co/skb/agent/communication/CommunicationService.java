package kr.co.skb.agent.communication;

import kr.co.skb.agent.domain.KickboardLocation;

public interface CommunicationService {
    public void sendKickboard() throws Exception;
    public void sendKickboardUse(String use) throws Exception;
    public void sendKickboardLocation(KickboardLocation kickboardLocation) throws Exception;
}
