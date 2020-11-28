package ru.spbstu.antufievsemen.courseworkSMO_2.archive;

import java.util.LinkedList;
import ru.spbstu.antufievsemen.courseworkSMO_2.source.request.Request;
import ru.spbstu.antufievsemen.courseworkSMO_2.timestamp.DeviceStamp;

public class DeviceArchiveRequest {

  private LinkedList<DeviceStamp> listDeviceStamp;

  public DeviceArchiveRequest() {
    listDeviceStamp = new LinkedList<>();
  }

  public void writeInArchive(Request request, long timeToCome, long timeForWorking) {
    listDeviceStamp.add(new DeviceStamp(timeToCome, request, timeForWorking));
  }

  public void showInfo() {
    for (DeviceStamp deviceStamp : listDeviceStamp) {
      System.out.println(deviceStamp.toString());
    }
    System.out.println();
  }
}
