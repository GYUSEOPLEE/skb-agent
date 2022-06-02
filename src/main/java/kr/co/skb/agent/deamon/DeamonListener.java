package kr.co.skb.agent.deamon;

import kr.co.skb.agent.util.KickboardLocationUtil;
import kr.co.skb.agent.util.KickboardUseUtil;
import kr.co.skb.agent.util.KickboardUseWatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Component
@WebListener
@RequiredArgsConstructor
public class DeamonListener implements ServletContextListener, Runnable {
    private Thread thread;
    private boolean isShutdown = false;
    private ServletContext sc;
    private final KickboardUseUtil kickboardUseUtil;
    private final KickboardUseWatchService kickboardUseWatchService;
    private final KickboardLocationUtil kickboardLocationUtil;

    public void startDaemon() {
        if(thread == null) {
            thread = new Thread(this, "Deamon StartThread");
        }

        if(!thread.isAlive()) {
            thread.start();
        }
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();

        while (currentThread == thread && !this.isShutdown) {
            kickboardUseUtil.kickboardUse();
            kickboardUseWatchService.kickboardStartSend();
            kickboardLocationUtil.kickboardLocation();
        }
    }

    @Override
    public void contextInitialized (ServletContextEvent event) {
        sc = event.getServletContext();
        startDaemon();
    }

    @Override
    public void contextDestroyed (ServletContextEvent event) {
        this.isShutdown = true;
        try
        {
            thread.join();
            thread = null;
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }
}
