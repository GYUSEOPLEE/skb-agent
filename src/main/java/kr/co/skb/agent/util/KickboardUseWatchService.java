package kr.co.skb.agent.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.List;

public class KickboardUseWatchService {
    final static String file = "/home/pi/Desktop/kickboard/KickboardUse.txt";
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
                    //buffer.toString();을 보내줘야한다
                }

                if (!watchKey.reset()) {
                    break;
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                watchService.close();
            } catch (Exception e) {
                e.printStackTrace();
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
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return buffer;
    }
}