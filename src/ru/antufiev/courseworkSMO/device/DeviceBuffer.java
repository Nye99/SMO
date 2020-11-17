package ru.antufiev.courseworkSMO.device;

import java.util.ArrayList;
import ru.antufiev.courseworkSMO.buffer.Buffer;
import ru.antufiev.courseworkSMO.globaltime.GlobalTimer;
import ru.antufiev.courseworkSMO.source.SourceBuffer;

public class DeviceBuffer extends Thread {

  private int size;
  private Buffer buffer;
  private GlobalTimer globalTimer;
  private SourceBuffer sourceBuffer;

  public DeviceBuffer(int size, Buffer buffer, GlobalTimer globalTimer, SourceBuffer sourceBuffer) {
    this.buffer = buffer;
    this.size = size;
    this.globalTimer = globalTimer;
    this.sourceBuffer = sourceBuffer;
  }

  @Override
  public void run() {
    ArrayList<Thread> deviceBuffer = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      Thread thread = new Thread(new Device(i, this.buffer, globalTimer, sourceBuffer));
      thread.setPriority(thread.MAX_PRIORITY - i);
      deviceBuffer.add(thread);
    }
    deviceBuffer.forEach(Thread::start);
  }

}
