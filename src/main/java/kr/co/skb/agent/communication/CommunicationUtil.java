package kr.co.skb.agent.communication;

import kr.co.skb.agent.device.KickboardLocation;
import kr.co.skb.agent.device.KickboardUse;
import kr.co.skb.agent.util.Kickboard;

public interface CommunicationUtil {
    public boolean request(Kickboard kickboard);
    public boolean request(KickboardUse kickboardUse);
    public boolean request(KickboardLocation kickboardLocation);
}
