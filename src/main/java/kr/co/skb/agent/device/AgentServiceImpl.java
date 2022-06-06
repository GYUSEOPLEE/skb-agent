package kr.co.skb.agent.device;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import kr.co.skb.agent.communication.CommunicationService;
import kr.co.skb.agent.domain.KickboardLocation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

@Log4j2
@Service
public class AgentServiceImpl implements AgentService {
    @Autowired CommunicationService communicationService;
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
                            communicationService.sendKickboardUse("N");
                        } else {
                            isUsing = true;
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
            // TODO: 위치 정보 읽기
            bufferedReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(locationPath)));
            String readData = bufferedReader.readLine().trim();
            String[] location = readData.split(", ");

            KickboardLocation kickboardLocation = KickboardLocation.builder()
                    .dateTime(LocalDateTime.now())
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