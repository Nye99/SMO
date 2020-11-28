package ru.spbstu.antufievsemen.courseworkSMO_2.timestamp;

import ru.spbstu.antufievsemen.courseworkSMO_2.source.request.Request;

public class DeviceStamp {

  private Long timeComeToDevice;
  private Request request;
  private Long timeForWorking;

  public DeviceStamp(long timeComeToDevice, Request request, long timeForWorking) {
    this.timeComeToDevice = timeComeToDevice;
    this.request = request;
    this.timeForWorking = timeForWorking;
  }

  public long getTimeComeToDevice() {
    return timeComeToDevice;
  }

  public Request getRequest() {
    return request;
  }

  public long getTimeForWorking() {
    return timeForWorking;
  }

  @Override
  public String toString() {
    return "DeviceStamp{" +
            "timeComeToDevice=" + timeComeToDevice +
            ", request=" + request.toString() +
            ", timeForWorking=" + timeForWorking +
            '}';
  }
}
