package kr.co.skb.agent.util;

import kr.co.skb.agent.communication.CommunicationService;
import kr.co.skb.agent.domain.AgentPath;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

@Log4j2
@EnableAsync
@Component
public class SendLocationScheduler {
    @Autowired private CommunicationService communicationService;
    @Autowired private CommunicationUtil communicationUtil;
    @Autowired private AgentPath agentPath;

    // 매시간 0초부터 5초마다 동작
    @Scheduled(cron = "0/5 * * * * *")
    @Async // 병렬 동작
    public void sendKickboardLocation() {
        if (log.isTraceEnabled()) {
            log.trace("5초마다 Scheduler 동작: {}", LocalDateTime.now());
        }

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream("/home/pi/Desktop/location.txt")));

            String location = bufferedReader.readLine().trim();

            // TODO: 킥보드 위치 정보 송신

        } catch(Exception e) {
            log.error("킥보드 위치 정보 송신 실패");
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                if (log.isTraceEnabled()) {
                    log.trace(e.getMessage());
                }
            }
        }
    }
}