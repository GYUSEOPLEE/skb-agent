package kr.co.skb.agent.util;

import org.python.util.PythonInterpreter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class KickboardLocationUtil {
    @Async
    public void kickboardLocation() {
        PythonInterpreter pythonInterpreter = new PythonInterpreter();
        pythonInterpreter.execfile("tqs.py");
        pythonInterpreter.exec("main()");
    }
}
