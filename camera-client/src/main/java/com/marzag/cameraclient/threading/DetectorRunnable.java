package com.marzag.cameraclient.threading;

import com.marzag.cameraclient.CameraClientApplication;
import com.marzag.cameraclient.enums.GPIOState;
import com.marzag.cameraclient.exceptions.FailedToRunRaspistillException;
import com.marzag.cameraclient.util.CameraHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class DetectorRunnable implements Runnable {


    CameraHandler cameraHandler = new CameraHandler();

    private static final Logger LOGGER = LoggerFactory.getLogger(DetectorRunnable.class);
    private volatile boolean running = false;

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        {
            int detectorPin = 18;
            while (running) {
                if (getState(detectorPin).equals(GPIOState.HIGH)) {
                    try {
                        new Thread(cameraHandler.takePhoto("Detected " + new Date().toString() + ".jpeg")).run();
                    } catch (FailedToRunRaspistillException e) {
                        CameraClientApplication.log.error(e.getMessage());
                    } catch (IOException e) {
                        CameraClientApplication.log.error(e.getMessage());
                    } catch (InterruptedException e) {
                        CameraClientApplication.log.error(e.getMessage());
                    }

                }
                try {
                    Thread.sleep(100l);
                } catch (InterruptedException e) {
                    CameraClientApplication.log.error(e.getMessage());
                }
            }
        }
    }

    public void start() {
        running = true;
    }

    private String runCommand(String cmd) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }

        return "";
    }

    private GPIOState getState(int pinNo) {
        String cmdResult = "";
        if (System.getProperty("os.name").equals("Linux")) {
            cmdResult = runCommand("raspi-gpio get " + pinNo);
        } else
            cmdResult = runCommand("whoami");
        if (cmdResult.contains("GPIO " + pinNo + ": level=1")) {
            return GPIOState.HIGH;
        } else
            return GPIOState.LOW;
    }
}
