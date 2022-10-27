package com.marzag.cameraclient.util;

import com.marzag.cameraclient.CameraClientApplication;
import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.IOType;
import com.pi4j.io.gpio.digital.*;
import jdk.internal.event.Event;

import java.util.EventListener;
import java.util.Properties;


public class GpioHandler {
    
    /*
    * sensor connected to:
    * 2(5V PWR)
    * 6 (GND)
    * 12 (GPIO 18, WiringPi 1)
    * */
    //trzeba bedzie sprawdzic na ktory pin reaguje, gpio dziala ale sa problemy z przechwyceniem sygnalu na pi4j
    final int DIGITAL_INPUT_PIN = 1;
    DigitalInput input;
 public void init(){

     Context pi4j = Pi4J.newAutoContext();

     DigitalInputConfig config = DigitalInput.newConfigBuilder(pi4j)
             .address(DIGITAL_INPUT_PIN)
             .pull(PullResistance.PULL_UP)
             .build();

     DigitalInputProvider digitalInputProvider = pi4j.provider(IOType.DIGITAL_INPUT);

     input = digitalInputProvider.create(config);
     CameraClientApplication.log.info("Pi4j Context configuration done.");

     //listening probably should be in other thread in loop
     input.addListener(e -> {
         Integer count = (Integer) e.source().metadata().get("count").value();
         if (e.state() == DigitalState.HIGH) {
             CameraClientApplication.log.info("Signal is HIGH");
         }else
             CameraClientApplication.log.info("Signal is LOW");
     });

     CameraClientApplication.log.info("Starting signal is " + input.state());
 }


}
