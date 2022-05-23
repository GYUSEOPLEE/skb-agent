package kr.co.skb.agent.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.nio.file.*;

@Log4j2
@Component
@Scope("prototype")
public class WatchUtil implements Runnable {
    @Value("${path.watch}")
    private String watchPath;

    @Value("${path.save}")
    private String savePath;

    @Override
    public void run() {
        WatchService watchService = null;

        try {
            watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(watchPath);
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            while (true) {
                WatchKey watchKey = watchService.take();
                log.info("파일 변경");

                // TODO: 파일 쓰기

                if (!watchKey.reset()) {
                    break;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                watchService.close();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}
