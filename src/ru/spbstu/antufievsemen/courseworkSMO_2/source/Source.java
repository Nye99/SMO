package ru.spbstu.antufievsemen.courseworkSMO_2.source;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import ru.spbstu.antufievsemen.courseworkSMO_2.archive.SourceArchiveRequest;
import ru.spbstu.antufievsemen.courseworkSMO_2.buffer.Buffer;
import ru.spbstu.antufievsemen.courseworkSMO_2.globaltime.GlobalTime;
import ru.spbstu.antufievsemen.courseworkSMO_2.source.request.Request;

public class Source extends Thread {

  private int number;
  private static AtomicInteger counterRequest;
  private int constraint;
  private long timeFree;
  private Buffer buffer;
  private SourceArchiveRequest sourceArchiveRequest;

  public Source(int number, int constraint, Buffer buffer, SourceArchiveRequest sourceArchiveRequest) {
    this.number = number;
    this.constraint = constraint;
    this.buffer = buffer;
    this.sourceArchiveRequest = sourceArchiveRequest;
    counterRequest = new AtomicInteger(1);
  }

  public Request createRequest() {
    return new Request(number, counterRequest.getAndIncrement(), GlobalTime.startOrGetTime());
  }

  public long calculatedTime(int bound) {
    return ThreadLocalRandom.current().nextInt(bound);
  }

  public void showInfoRequest(Request request) {
    StringBuffer stringBuilder = new StringBuffer();
    stringBuilder.append("{Source: ").append(request.getNumber());
    stringBuilder.append(" create request number: ").append(request.getCounterNumber());
    stringBuilder.append(" time: ").append(request.getCreatingTime())
    .append("}");
    System.out.println(stringBuilder.toString());
  }

  @Override
  public void run() {
    int counterRequest = 0;
    while (counterRequest++ < constraint) {
      Request request = createRequest();
      showInfoRequest(request);
      sourceArchiveRequest.writeRequestArchive(request);
      buffer.takeRequest(request);
      try {
        Thread.sleep(calculatedTime(5));
      } catch (InterruptedException e) {
      }
    }
  }
}
