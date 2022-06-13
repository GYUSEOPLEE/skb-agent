package kr.co.skb.agent.device;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import kr.co.skb.agent.communication.CommunicationService;
import kr.co.skb.agent.domain.KickboardLocation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

@Log4j2
@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final CommunicationService communicationService;
    @Value("${path-location}") private String locationPath;
    private static boolean isUsing = false;

    @Override
    public void checkKickboardUse() {
        final GpioController controller = GpioFactory.getInstance();
        final GpioPinDigitalInput pin = controller.provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_DOWN);

        pin.addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(
                    GpioPinDigitalStateChangeEvent event) {
                try {
                    PinState pinState = event.getState();
                    if (pinState.isHigh()) {
                        if (isUsing) {
                            isUsing = false;
                            log.info("use      : N");

                            communicationService.sendKickboardUse("N");
                        } else {
                            isUsing = true;
                            log.info("use      : Y");

                            communicationService.sendKickboardUse("Y");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void checkKickboardLocation() throws NumberFormatException {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(locationPath)));
            String readData = bufferedReader.readLine().trim();
            String[] location = readData.split(", ");

            KickboardLocation kickboardLocation = KickboardLocation.builder()
                    .dateTime(LocalDateTime.now().withNano(0))
                    .latitude(Double.parseDouble(location[0]))
                    .longitude(Double.parseDouble(location[1]))
                    .build();

            communicationService.sendKickboardLocation(kickboardLocation);
        } catch(Exception e) {
            log.error("킥보드 위치 정보 송신 실패");
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
}