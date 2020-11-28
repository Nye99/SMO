package ru.spbstu.antufievsemen.courseworkSMO_2.buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import ru.spbstu.antufievsemen.courseworkSMO_2.archive.BufferArchiveRequest;
import ru.spbstu.antufievsemen.courseworkSMO_2.archive.SourceArchiveRequest;
import ru.spbstu.antufievsemen.courseworkSMO_2.source.request.Request;

public class Buffer {

  private List<Request> buffer;
  private int size;
  private AtomicInteger pointer;
  private BufferArchiveRequest bufferArchiveRequest;

  public Buffer(int size, BufferArchiveRequest bufferArchiveRequest) {
    this.size = size;
    this.bufferArchiveRequest = bufferArchiveRequest;
    buffer = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      buffer.add(null);
    }
    pointer = new AtomicInteger(0);
  }

  public boolean isClear() {
    for (Request request : buffer) {
      if (request != null) {
        return false;
      }
    }
    return true;
  }

  public synchronized void takeRequest(Request request) {
    for (int i = 0; i < size; i++) {
      int valueTemp = pointer.get();
      if (buffer.get(valueTemp) == null) {
        buffer.set(valueTemp, request);
        incrementPointer();
        showInfoBuffer(buffer);
        return;
      }
      incrementPointer();
    }
    cancelRequest(request);
  }

  public synchronized void cancelRequest(Request request) {
    int min = Integer.MAX_VALUE;
    Request removeRequest = null;
    for (Request request1 : buffer) {
      if (request1.getCounterNumber() < min) {
        min = request1.getCounterNumber();
        removeRequest = request1;
      }
    }
    int index = buffer.indexOf(removeRequest);
    buffer.set(index, null);
    showInfoCancelRequest(removeRequest);
    bufferArchiveRequest.writeCancelRequestArchive(removeRequest);
    takeRequest(request);
  }

  public synchronized Request giveRequest() {
    int max = 0;
    Request removeRequest = null;
    for (Request request1 : buffer) {
      if (request1 != null && request1.getCounterNumber() > max) {
        max = request1.getCounterNumber();
        removeRequest = request1;
      }
    }
    if (removeRequest != null) {
      int index = buffer.indexOf(removeRequest);
      buffer.set(index, null);
      //time is running
      return removeRequest;
    }
    return null;
  }

  public int getPointer() {
    return pointer.get();
  }

  public synchronized void incrementPointer() {
    if (pointer.incrementAndGet() == size) {
      pointer.set(0);
    }
  }

  public void showInfoCancelRequest(Request request) {
    StringBuffer stringBuilder = new StringBuffer();
    stringBuilder.append("{Cancel ")
            .append(request.getNumber());
    stringBuilder.append(" request number: ")
            .append(request.getCounterNumber())
            .append("}");
    System.out.println(stringBuilder);
  }

  public StringBuffer showInfoRequest(Request request) {
    StringBuffer stringBuilder = new StringBuffer();
    stringBuilder.append("Source: ")
            .append(request.getNumber());
    stringBuilder.append(" Request number: ")
            .append(request.getCounterNumber());
    stringBuilder.append(" Creation time: ")
            .append(request.getCreatingTime());
    return stringBuilder;
  }

  public void showInfoBuffer(List<Request> buffer) {
    for (int i = 0; i < size; i++) {
      StringBuffer stringBuffer = new StringBuffer()
              .append("(")
              .append(i + 1)
              .append(". ");
      if (buffer.get(i) == null) {
        stringBuffer.append("...");
      } else {
        stringBuffer.append(showInfoRequest(buffer.get(i)));
      }
      stringBuffer.append(")");
      System.out.println(stringBuffer.toString());
    }
  }
}
