package kr.co.skb.agent.communication;

import kr.co.skb.agent.device.KickboardLocation;
import kr.co.skb.agent.device.KickboardUse;
import kr.co.skb.agent.util.Kickboard;

public class CommunicationUtilImpl implements CommunicationUtil {
    @Override
    public boolean request(Kickboard kickboard) {
        return false;
    }

    @Override
    public boolean request(KickboardUse kickboardUse) {
        return false;
    }

    @Override
    public boolean request(KickboardLocation kickboardLocation) {
        return false;
    }
}
