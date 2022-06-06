package kr.co.skb.agent.util;

import kr.co.skb.agent.communication.CommunicationService;
import kr.co.skb.agent.device.AgentService;
import lombok.extern.log4j.Log4j2;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@Log4j2
@EnableAsync
@Component
public class SendLocationScheduler {
    @Value("${path-info}") private String infoPath;
    @Autowired private CommunicationService communicationService;
    @Autowired private AgentService agentService;

    private boolean first = true;

    // 매시간 0초부터 5초마다 동작
    @Scheduled(cron = "0/5 * * * * *")
    public void sendKickboardLocation() throws Exception {
        if (first) {
            first = false;

            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        new FileInputStream(infoPath)));
                CommunicationUtil.info.put("systemIp", bufferedReader.readLine().trim().split("=")[1]);
                CommunicationUtil.info.put("helmetIp", bufferedReader.readLine().trim().split("=")[1]);
            } catch (IOException e) {

            }

            afterPropertiesSet();
            agentService.checkKickboardUse();
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