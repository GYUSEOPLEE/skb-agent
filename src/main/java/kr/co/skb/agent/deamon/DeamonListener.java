package kr.co.skb.agent.deamon;

import kr.co.skb.agent.util.KickboardUseUtil;
import kr.co.skb.agent.util.KickboardUseWatchService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DeamonListener implements ServletContextListener, Runnable {
    private Thread StartThread;
    private Thread StartWatchThread;
    private boolean isShutdown = false;
    private ServletContext sc;
    private KickboardUseUtil kickboardUseUtil;
    private KickboardUseWatchService kickboardUseWatchService;

    public void startDaemon() {
        if(StartThread == null) {
            StartThread = new Thread(this, "Deamon StartThread");
        }

        if(StartWatchThread == null) {
            StartWatchThread = new Thread(this, "Deamon StartWatchThread");
        }

        if(!StartThread.isAlive()) {
            StartThread.start();
        }

        if(!StartWatchThread.isAlive()) {
            StartWatchThread.start();
        }
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();

        while (!this.isShutdown) {
            kickboardUseUtil.kickboardUse();
            kickboardUseWatchService.kickboardStartSend();
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
            StartThread.join();
            StartThread = null;
            StartWatchThread.join();
            StartWatchThread = null;
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }
}
