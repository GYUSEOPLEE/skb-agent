package kr.co.skb.agent.deamon;

import kr.co.skb.agent.util.KickboardUseUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DeamonListener implements ServletContextListener, Runnable {
    private Thread thread;
    private boolean isShutdown = false;
    private ServletContext sc;
    private KickboardUseUtil kickboardUseUtil;

    public void startDaemon() {
        if(thread == null) {
            thread = new Thread(this, "Deamon thread");
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
        }
    }

    public void contextInitialized (ServletContextEvent event) {
        sc = event.getServletContext();
        startDaemon();
    }

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
