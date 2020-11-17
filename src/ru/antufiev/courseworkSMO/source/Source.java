package ru.antufiev.courseworkSMO.source;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;
import ru.antufiev.courseworkSMO.buffer.Buffer;
import ru.antufiev.courseworkSMO.globaltime.GlobalTimer;
import ru.antufiev.courseworkSMO.resultcounter.ResultCounter;
import ru.antufiev.courseworkSMO.source.request.Request;

public class Source implements Runnable {

  private int number;
  private Buffer buffer;
  private GlobalTimer globalTimer;
  private ResultCounter resultCounter;

  public Source(int number, Buffer buffer, GlobalTimer globalTimer, ResultCounter resultCounter) {
    this.number = number;
    this.buffer = buffer;
    this.globalTimer = globalTimer;
    this.resultCounter = resultCounter;
  }

  private Request createRequest() {
    return new Request(number, globalTimer, resultCounter);
  }

  private int calculatedWaitTime(int initA, int initB) {
    AtomicInteger a = new AtomicInteger(initA);
    AtomicInteger b = new AtomicInteger(initB);
    IntBinaryOperator func = (x, y) -> (int) (y + (x - y) * (ThreadLocalRandom.current().nextDouble() % 2));
    return b.accumulateAndGet(a.get(), func);
  }


  @Override
  public void run() {
    while (globalTimer.isContinued()) {
      while (globalTimer.isPauseOn()) {
      }
      buffer.addRequest(createRequest());
      try {
        int a = calculatedWaitTime(0, 3);
//        System.out.println(a + " Source");
        Thread.sleep(2);
      } catch (InterruptedException ignored) {
      }
    }
  }

}
