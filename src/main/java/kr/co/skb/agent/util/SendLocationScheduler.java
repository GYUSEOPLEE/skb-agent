package kr.co.skb.agent.util;

import kr.co.skb.agent.communication.CommunicationService;
import kr.co.skb.agent.device.AgentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@EnableAsync
@Component
public class SendLocationScheduler {
    @Autowired private CommunicationService communicationService;
    @Autowired private AgentService agentService;

    private boolean first = true;

    // 매시간 0초부터 5초마다 동작
    @Scheduled(cron = "0/5 * * * * *")
    @Async
    public void sendKickboardLocation() throws Exception {
        if (first) {
            afterPropertiesSet();
            agentService.checkKickboardUse();
    //        PythonInterpreter pythonInterpreter = new PythonInterpreter();
    //        pythonInterpreter.execfile("tqs.py");
    //        pythonInterpreter.exec("main()");
            first = false;
        }

        agentService.checkKickboardLocation();
    }

    public void afterPropertiesSet() throws Exception {
        for (int i = 0; i < 5; i++) {
            try {
                communicationService.sendKickboard();
                break;
            } catch (Exception e) {
                log.error("킥보드 정보 송신 실패  " + i + "/" + 5);
                Thread.sleep(1000);
            }
        }
    }
}