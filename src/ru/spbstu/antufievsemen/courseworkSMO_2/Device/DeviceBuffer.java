package ru.spbstu.antufievsemen.courseworkSMO_2.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import ru.spbstu.antufievsemen.courseworkSMO_2.buffer.Buffer;
import ru.spbstu.antufievsemen.courseworkSMO_2.counter.DeviceCounter;
import ru.spbstu.antufievsemen.courseworkSMO_2.source.SourceBuffer;

public class DeviceBuffer {

  private int size;
  private AtomicInteger pointer;
  private DeviceCounter deviceCounter;
  private SourceBuffer sourceBuffer;
  private Buffer buffer;
  private List<Device> deviceList;

  public DeviceBuffer(int size, SourceBuffer sourceBuffer, Buffer buffer, DeviceCounter deviceCounter) {
    this.size = size;
    pointer = new AtomicInteger(0);
    this.deviceCounter = deviceCounter;
    this.sourceBuffer = sourceBuffer;
    this.buffer = buffer;
    deviceList = new ArrayList<>();
  }

  public int getPointer() {
    return pointer.get();
  }

  public void incrementPointer() {
    if (pointer.incrementAndGet() == size) {
      pointer.set(0);
    }
  }

  public boolean isSourceWorking(){
    return sourceBuffer.isAlive();
  }

  public int freeDevice() {
    long time = System.currentTimeMillis();
    for (Device device : deviceList) {
      if (device.getFreeTime() < time) {
        return device.getNumber();
      }
    }
    return -1;
  }

  public void start() {
    for (int i = 0; i < size; i++) {
      Device device = new Device(i, deviceCounter, buffer, this);
      deviceList.add(device);
    }
    deviceList.forEach(Thread::start);
  }


}
