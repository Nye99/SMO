package ru.spbstu.antufievsemen.courseworkSMO_2.source;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import ru.spbstu.antufievsemen.courseworkSMO_2.buffer.Buffer;
import ru.spbstu.antufievsemen.courseworkSMO_2.counter.SourceCounter;
import ru.spbstu.antufievsemen.courseworkSMO_2.source.request.Request;

public class Source extends Thread {

  private int number;
  private static AtomicInteger counterRequest;
  private int constraint;
  private long timeFree;
  private Buffer buffer;
  private SourceCounter sourceCounter;

  public Source(int number, int constraint, Buffer buffer, SourceCounter sourceCounter) {
    this.number = number;
    this.constraint = constraint;
    this.buffer = buffer;
    this.sourceCounter = sourceCounter;
    counterRequest = new AtomicInteger(1);
  }

  public Request createRequest() {
    return new Request(number, counterRequest.getAndIncrement(), System.currentTimeMillis());
  }

  public long calculatedTime(int bound) {
    return ThreadLocalRandom.current().nextInt(bound);
  }

  public void showInfoRequest(Request request) {
    StringBuffer stringBuilder = new StringBuffer();
    stringBuilder.append("Source: ").append(request.getNumber());
    stringBuilder.append(" Request number: ").append(request.getCounterNumber());
    stringBuilder.append(" Creation time: ").append(request.getCreatingTime());
    System.out.println(stringBuilder.toString());
  }

  @Override
  public void run() {
    int counterRequest = 0;
    while (counterRequest++ < constraint) {
      Request request = createRequest();
      showInfoRequest(request);
      sourceCounter.readInfoRequest(request);
      buffer.takeRequest(request);
      try {
        Thread.sleep(calculatedTime(10));
      } catch (InterruptedException e) {
      }
    }
  }
}
