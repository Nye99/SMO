package ru.spbstu.antufievsemen.courseworkSMO_2.Device;

import java.util.concurrent.ThreadLocalRandom;
import ru.spbstu.antufievsemen.courseworkSMO_2.buffer.Buffer;
import ru.spbstu.antufievsemen.courseworkSMO_2.counter.DeviceCounter;
import ru.spbstu.antufievsemen.courseworkSMO_2.source.request.Request;

public class Device extends Thread {

  private int number;
  private DeviceCounter deviceCounter;
  private DeviceBuffer deviceBuffer;
  private Buffer buffer;
  private long freeTime;

  public Device(int number, DeviceCounter deviceCounter, Buffer buffer, DeviceBuffer deviceBuffer) {
    this.number = number;
    this.deviceCounter = deviceCounter;
    this.deviceBuffer = deviceBuffer;
    this.buffer = buffer;
  }

  public int getNumber() {
    return number;
  }

  public long getFreeTime() {
    return freeTime;
  }

  public long calculatedTime(int bound) {
    return ThreadLocalRandom.current().nextInt(bound);
  }

  @Override
  public void run() {
    while (deviceBuffer.isSourceWorking() || !buffer.isClear()) {
      if (deviceBuffer.getPointer() == number) {
        if (!buffer.isClear()) {
          Request request = buffer.giveRequest();
          deviceBuffer.incrementPointer();
          try {
            Thread.sleep(calculatedTime(10));
          } catch (InterruptedException e) {
          }
          deviceCounter.readInfoRequest(number, request);
        }
      }
    }
  }
}
