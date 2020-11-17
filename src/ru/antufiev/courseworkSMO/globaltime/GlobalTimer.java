package ru.antufiev.courseworkSMO.globaltime;

import java.util.concurrent.atomic.AtomicBoolean;

public class GlobalTimer {

  private long timeStart;
  private long currentTime;
  private long finishTime;
  private AtomicBoolean isEnd;
  private AtomicBoolean pauseOn;

  public GlobalTimer() {
    timeStart = System.currentTimeMillis();
    isEnd = new AtomicBoolean(false);
    pauseOn = new AtomicBoolean(false);
  }

  public long getTimeStart() {
    return timeStart;
  }

  public void setTimeStart(long timeStart) {
    this.timeStart = timeStart;
  }

  public long getCurrentTime() {
    return currentTime;
  }

  public void setCurrentTime(long currentTime) {
    this.currentTime = currentTime;
  }

  public long getFinishTime() {
    return finishTime;
  }

  public void setFinishTime(long finishTime) {
    this.finishTime = finishTime;
  }

  public boolean isPauseOn(){
    return pauseOn.get();
  }

  public void setPauseOn() {
    pauseOn.set(true);
  }
  public void setPauseOff() {
    pauseOn.set(false);
  }

  public boolean isContinued() {
    return !isEnd.get();
  }

  public void setEnd(boolean end) {
    isEnd.set(end);
  }
}
