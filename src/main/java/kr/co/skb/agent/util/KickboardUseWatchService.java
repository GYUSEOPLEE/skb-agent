package kr.co.skb.agent.util;

import kr.co.skb.agent.communication.CommunicationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.List;

@Component
@Log4j2
public class KickboardUseWatchService {
    final static String file = "/home/pi/Desktop/kickboard/KickboardUse.txt";
    @Autowired private CommunicationService communicationService;

    @Async
    public void kickboardStartSend(){
        WatchService watchService = null;
        StringBuffer buffer = new StringBuffer();

        try {
            watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get("/home/pi/Desktop/kickboard");
            path.register(watchService,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            while (true) {
                WatchKey watchKey = watchService.take();
                List<WatchEvent<?>> events = watchKey.pollEvents();
                for (WatchEvent<?> event : events) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path context = (Path) event.context();
                    buffer = fileReader();
                    communicationService.sendKickboardUse(buffer.toString());
                }

                if (!watchKey.reset()) {
                    break;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (watchService != null) {
                    watchService.close();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    private StringBuffer fileReader(){
        BufferedReader bufferedReader = null;
        StringBuffer buffer = new StringBuffer();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            buffer.replace(0, 1000, bufferedReader.readLine());
            System.out.println(buffer);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(bufferedReader != null){
                    bufferedReader.close();
                }
            } catch (Exception e){
                log.error(e.getMessage());
            }
        }

        return buffer;
    }
}