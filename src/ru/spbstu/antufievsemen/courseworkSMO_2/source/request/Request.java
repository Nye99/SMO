package ru.spbstu.antufievsemen.courseworkSMO_2.source.request;

import ru.spbstu.antufievsemen.courseworkSMO_2.globaltime.GlobalTime;

public class Request {
  private int counterNumber;
  private int number;
  private long creatingTime;

  public Request(int number, int counterNumber, long creatingTime) {
    this.number = number;
    this.counterNumber = counterNumber;
    this.creatingTime = (creatingTime - GlobalTime.START_TIME);
  }

  public int getCounterNumber() {
    return counterNumber;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public long getCreatingTime() {
    return creatingTime;
  }

  public void showInfo() {
    System.out.println("Request: " + number + "time: " + creatingTime);
  }
}
