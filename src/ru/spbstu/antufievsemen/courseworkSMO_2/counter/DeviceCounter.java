package ru.spbstu.antufievsemen.courseworkSMO_2.counter;

import java.util.HashMap;
import java.util.Map;
import ru.spbstu.antufievsemen.courseworkSMO_2.source.request.Request;

public class DeviceCounter {
  private Map<Integer, Long> requestTimeMap;
  private Map<Integer, Integer> deviceRequestMap;

  public DeviceCounter() {
    requestTimeMap = new HashMap<>();
    deviceRequestMap = new HashMap<>();
  }

  public void readInfoRequest(int numberDevice, Request request) {
    int number = request.getNumber();
    requestTimeMap.put(number, System.currentTimeMillis());
    deviceRequestMap.put(numberDevice, number);
  }
}
