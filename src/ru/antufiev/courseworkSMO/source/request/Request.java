package ru.antufiev.courseworkSMO.source.request;

import java.util.concurrent.atomic.AtomicLong;
import ru.antufiev.courseworkSMO.globaltime.GlobalTimer;
import ru.antufiev.courseworkSMO.resultcounter.ResultCounter;
import ru.antufiev.courseworkSMO.source.SourceBuffer;

public class Request {

  private long createTime;
  private long comingBufferTime;
  private int requestNumber;
  private long finishTime;
  private int numberSource;
  private int numberDevice;
  private ResultCounter resultCounter;
  private GlobalTimer globalTimer;

  public Request(int numberSource, GlobalTimer globalTimer, ResultCounter resultCounter) {
    this.numberSource = numberSource;
    createTime = System.currentTimeMillis();
    this.resultCounter = resultCounter;
    this.globalTimer = globalTimer;
    this.requestNumber = SourceBuffer.requestNumber.incrementAndGet();
  }

  public Request(){}

  public long getComingBufferTime() {
    return comingBufferTime;
  }

  public int getRequestNumber() {
    return requestNumber;
  }

  public void setComingBufferTime(long comingBufferTime) {
    this.comingBufferTime = comingBufferTime;
  }

  public long getFinishTime() {
    return finishTime;
  }

  public void setFinishTime(long finishTime) {
    this.finishTime = finishTime;
  }

  public long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }

  public int getNumberSource() {
    return numberSource;
  }

  public void setNumberSource(int numberSource) {
    this.numberSource = numberSource;
  }

  public int getNumberDevice() {
    return numberDevice;
  }

  public void setNumberDevice(int numberDevice) {
    this.numberDevice = numberDevice;
  }

  public synchronized void writeInfo() {
    globalTimer.setCurrentTime(System.currentTimeMillis());
    resultCounter.writeInfo(this);
  }
}

