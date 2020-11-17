package ru.antufiev.courseworkSMO.device;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;
import ru.antufiev.courseworkSMO.buffer.Buffer;
import ru.antufiev.courseworkSMO.globaltime.GlobalTimer;
import ru.antufiev.courseworkSMO.source.SourceBuffer;
import ru.antufiev.courseworkSMO.source.request.Request;

public class Device implements Runnable {

  private int number;
  private Buffer buffer;
  private GlobalTimer globalTimer;
  private SourceBuffer sourceBuffer;

  public Device(int number, Buffer buffer, GlobalTimer globalTimer, SourceBuffer sourceBuffer) {
    this.number = number;
    this.buffer = buffer;
    this.globalTimer = globalTimer;
    this.sourceBuffer = sourceBuffer;
  }

  private int calculateTime() {
    IntBinaryOperator scale = (x, y) -> (x * y);
    AtomicInteger result = new AtomicInteger(1);
    result.accumulateAndGet((int) ((-1 / 0.25) * Math.log(ThreadLocalRandom.current().nextDouble(1))), scale);
    return result.get();
  }

  @Override
  public void run() {
    while (globalTimer.isContinued() || !sourceBuffer.isOver()) {
      while (globalTimer.isPauseOn()) {
      }
      if (buffer.getSize() != 0) {
        Request request = buffer.getRequest();
        if (request != null) {
          System.out.println("Take " + request.getRequestNumber());
          try {
            request.setNumberDevice(this.number);
            int timeWork = calculateTime();
            request.setFinishTime(System.currentTimeMillis());
            request.writeInfo();
            Thread.sleep(10);
          } catch (InterruptedException e) {
          }
        }
      }
    }
  }

}
