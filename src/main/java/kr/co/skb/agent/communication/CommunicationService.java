package kr.co.skb.agent.communication;

public interface CommunicationService {
    public void sendKickboard() throws Exception;
    public void sendKickboardUse(String use) throws Exception;
    public void sendKickboardLocation() throws Exception;
}
