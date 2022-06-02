package kr.co.skb.agent.util;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import java.io.*;

public class KickboardUseUtil {
    final static File file = new File(File.separator + "home" + File.separator + "pi" + File.separator + "Desktop" + File.separator + "KickboardUse.txt");
    public void kickboardUse () {
        final GpioController controller = GpioFactory.getInstance();
        final GpioPinDigitalInput pin =
                controller.provisionDigitalInputPin(RaspiPin.GPIO_00,
                        PinPullResistance.PULL_DOWN);

        try {
            pin.addListener(new GpioPinListenerDigital() {
                public void handleGpioPinDigitalStateChangeEvent(
                        GpioPinDigitalStateChangeEvent event) {
                    PinState pinState = event.getState();
                    FileReader fileReader = null;
                    FileWriter fileWriter = null;
                    if (pinState.isHigh()) {
                        try {
                            StringBuffer buffer = fileReader(fileReader);
                            String str = compareValues(buffer);

                            if(str != null) {
                                fileWrite(fileWriter, str);
                            }

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private StringBuffer fileReader(FileReader fileReader) throws IOException {
        fileReader = new FileReader(file);
        StringBuffer str = new StringBuffer();
        int read;
        char[] buffer = new char[10];

        while ((read = fileReader.read(buffer)) != -1) {
            String data = new String(buffer, 0, read);
            str.append(data);
        }

        return  str;
    }

    private String compareValues(StringBuffer str) {
        if(str.toString().trim().equals("Y")) {
            return "N";
        } else if(str.toString().trim().equals("N")) {
            return "Y";
        }

        return null;
    }

    private void fileWrite(FileWriter fileWriter, String result) throws IOException {
        fileWriter = new FileWriter(file);
        fileWriter.write(result);
        fileWriter.flush();

        fileWriter.close();
    }
}
