package ru.spbstu.antufievsemen.courseworkSMO_2.Device;

import java.util.concurrent.ThreadLocalRandom;
import ru.spbstu.antufievsemen.courseworkSMO_2.buffer.Buffer;
import ru.spbstu.antufievsemen.courseworkSMO_2.archive.DeviceArchiveRequest;
import ru.spbstu.antufievsemen.courseworkSMO_2.globaltime.GlobalTime;
import ru.spbstu.antufievsemen.courseworkSMO_2.source.request.Request;

public class Device extends Thread {

  private int number;
  private DeviceArchiveRequest deviceArchiveRequest;
  private DeviceBuffer deviceBuffer;
  private Buffer buffer;

  public Device(int number, DeviceArchiveRequest deviceArchiveRequest, Buffer buffer, DeviceBuffer deviceBuffer) {
    this.number = number;
    this.deviceArchiveRequest = deviceArchiveRequest;
    this.deviceBuffer = deviceBuffer;
    this.buffer = buffer;
  }

  public int getNumber() {
    return number;
  }

  public long calculatedTime(int bound) {
    return ThreadLocalRandom.current().nextInt(bound);
  }

  public void showArrivalRequestTime(Request request, long currentTime) {
    StringBuffer stringBuffer = new StringBuffer()
            .append("{Device : ")
            .append(this.number)
            .append(" take request ")
            .append(request.getCounterNumber())
            .append(" time: ")
            .append(currentTime);
    System.out.println(stringBuffer);
  }

  public void showDeviceFreeTime() {
    long currentTime = GlobalTime.startOrGetTime();
    StringBuffer stringBuffer = new StringBuffer()
            .append("{Device : ")
            .append(this.number)
            .append(" time free: ")
            .append(currentTime)
            .append("}");
    System.out.println(stringBuffer);
  }

  @Override
  public void run() {
    while (deviceBuffer.isSourceWorking() || !buffer.isClear()) {
      if (deviceBuffer.getPointer() == number) {
        if (!buffer.isClear()) {
          try {
            long currentTime = GlobalTime.startOrGetTime();
            Request request = buffer.giveRequest();
            deviceBuffer.incrementPointer();
            showArrivalRequestTime(request, currentTime);
            long timeForWorking = calculatedTime(10);
            deviceArchiveRequest.writeInArchive(request, currentTime, timeForWorking);
            Thread.sleep(timeForWorking);
          } catch (InterruptedException e) {
          }
          showDeviceFreeTime();
        }
      }
    }
  }

}
