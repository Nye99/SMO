package ru.antufiev.courseworkSMO;

import java.util.Scanner;
import ru.antufiev.courseworkSMO.buffer.Buffer;
import ru.antufiev.courseworkSMO.device.DeviceBuffer;
import ru.antufiev.courseworkSMO.globaltime.GlobalTimer;
import ru.antufiev.courseworkSMO.interfaceinfo.InterfaceInfo;
import ru.antufiev.courseworkSMO.resultcounter.ResultCounter;
import ru.antufiev.courseworkSMO.source.SourceBuffer;

public class Main {

  public static void main(String[] args) {
    GlobalTimer globalTimer = new GlobalTimer();
    ResultCounter resultCounter = new ResultCounter(4, 3, globalTimer);
    InterfaceInfo interfaceInfo = new InterfaceInfo(globalTimer, resultCounter);
    Buffer buffer = new Buffer(4, interfaceInfo);
    SourceBuffer sourceBuffer = new SourceBuffer(4, buffer, globalTimer, resultCounter);
    DeviceBuffer deviceBuffer = new DeviceBuffer(3, buffer, globalTimer, sourceBuffer);
    sourceBuffer.start();
    deviceBuffer.start();
    Scanner scanner = new Scanner(System.in);
    while(true) {
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
      }
      globalTimer.setPauseOn();
      StringBuilder str = new StringBuilder(scanner.next());
      if (str.toString().equals("quit")) {
        globalTimer.setEnd(true);
        globalTimer.setPauseOff();
        break;
      }

      switch (str.toString()) {
        case ("pause") -> {
          globalTimer.setPauseOn();
        }
        case ("continue") -> {
          globalTimer.setPauseOff();
        }
        case ("buffer") -> {
          interfaceInfo.showInfoBuffer(buffer.getInfoBuffer());
        }
        case ("info") -> {
          interfaceInfo.showResult();
        }
        default -> {
          System.out.println("pause" + "\n" + "continue" + "\n" + "buffer" + "\n");
          System.out.println("info" + "\n");
        }
      }
    }
  }
}


