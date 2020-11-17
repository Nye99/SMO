package ru.antufiev.courseworkSMO.buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import ru.antufiev.courseworkSMO.interfaceinfo.InterfaceInfo;
import ru.antufiev.courseworkSMO.source.request.Request;

public class Buffer {

  private List<Request> buffer;
  private int size;
  private AtomicInteger pointer;
  private InterfaceInfo interfaceInfo;

  public Buffer(int size, InterfaceInfo interfaceInfo) {
    buffer = new ArrayList<>(size);
    this.size = size;
    pointer = new AtomicInteger(0);
    this.interfaceInfo = interfaceInfo;
    for (AtomicInteger i = new AtomicInteger(0); i.get() < size; i.incrementAndGet()) {
      buffer.add(null);
    }
  }

  public synchronized  Request getRequest() {
    if (getSize() != 0) {
      long max = Long.MIN_VALUE;
      Request requestRemove = null;
      for (Request request : buffer) {
        if (request != null && request.getComingBufferTime() > max) {
          requestRemove = request;
        }
      }
      int remove = buffer.indexOf(requestRemove);
      buffer.set(remove, null);
      return requestRemove;
    }
    return null;
  }

  public synchronized void addRequest(Request request) {
    System.out.println("Add " + request.getRequestNumber());
    request.setComingBufferTime(System.currentTimeMillis());
    int index = pointer.get();
    if (buffer.get(index) == null) {
      buffer.set(index, request);
      incrementPointer();
    } else {
      int counter = 0;
      while (buffer.get(pointer.get()) != null && counter != size + 1) {
        incrementPointer();
        counter++;
      }
      if (counter == size + 1) {
        denyRequest();
      }
      buffer.set(pointer.get(), request);
    }
    interfaceInfo.showInfoBuffer(buffer);
  }

  public synchronized void denyRequest() {
    long min = Long.MAX_VALUE;
    Request requestRemove = null;
    for (Request request : buffer) {
      if (request != null && request.getCreateTime() < min) {
        requestRemove = request;
      }
    }
    requestRemove.setNumberDevice(-1);
    requestRemove.writeInfo();
    int remove = buffer.indexOf(requestRemove);
    setPointer(remove);
    System.out.println("Deny " + requestRemove.getRequestNumber());
  }

  public int getSize() {
    return buffer.size();
  }

  public List<Request> getInfoBuffer() {
    return buffer;
  }

  private void incrementPointer() {
    pointer.incrementAndGet();
    if (pointer.get() == size) {
      pointer.set(0);
    }
  }

  private void setPointer(int pointer) {
    this.pointer.set(pointer);
  }
}
