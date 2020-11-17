package ru.antufiev.courseworkSMO.resultcounter;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import ru.antufiev.courseworkSMO.globaltime.GlobalTimer;
import ru.antufiev.courseworkSMO.source.request.Request;

public class ResultCounter {

  private ArrayList<AtomicInteger> arrayDevice;
  private ArrayList<AtomicInteger> arraySource;
  private AtomicInteger canceledRequest;


  public ResultCounter(int countSource, int countDevice, GlobalTimer globalTimer) {
    this.arrayDevice = new ArrayList<>(countDevice);
    this.arraySource = new ArrayList<>(countSource);
    for (int i = 0; i < countDevice; i++) {
      arrayDevice.add(new AtomicInteger(0));
    }
    for (int i = 0; i < countSource; i++) {
      arraySource.add(new AtomicInteger(0));
    }
    this.canceledRequest = new AtomicInteger(0);
  }

  public void writeInfo(Request request) {
    int numberDevice = request.getNumberDevice();
    if (numberDevice != -1) {
      arraySource.get(request.getNumberSource()).incrementAndGet();
      arrayDevice.get(request.getNumberDevice()).incrementAndGet();
    } else {
      arraySource.get(request.getNumberSource()).incrementAndGet();
      canceledRequest.incrementAndGet();
    }
  }

  public int getCanceledRequest() {
    return canceledRequest.get();
  }

  public void showResultDevice() {
    System.out.println("Device: ");
    System.out.println(arrayDevice.toString());
  }

  public void showResultSource() {
    System.out.println("Source: ");
    System.out.println(arraySource.toString());
  }
}
