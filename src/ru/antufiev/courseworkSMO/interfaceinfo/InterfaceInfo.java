package ru.antufiev.courseworkSMO.interfaceinfo;

import java.util.List;
import ru.antufiev.courseworkSMO.globaltime.GlobalTimer;
import ru.antufiev.courseworkSMO.resultcounter.ResultCounter;
import ru.antufiev.courseworkSMO.source.request.Request;

public class InterfaceInfo {

  private GlobalTimer globalTimer;
  private ResultCounter resultCounter;

  public InterfaceInfo(GlobalTimer globalTimer, ResultCounter resultCounter) {
    this.globalTimer = globalTimer;
    this.resultCounter = resultCounter;
  }

  public void showInfoRequest(Request request) {
    System.out.print("Request number: " + request.getRequestNumber());
    System.out.print(" Source number: " + request.getNumberSource());
    System.out.print(" Initial time: " + (request.getCreateTime() - globalTimer.getTimeStart()));
  }

  public void showInfoBuffer(List<Request> buffer) {
    int space = 1;
    for (Request request : buffer) {
      if (request != null) {
        System.out.print(space++ + ". ");
        showInfoRequest(request);
        System.out.println(" Buffer: " + (request.getComingBufferTime() - globalTimer.getTimeStart()));
      } else {
        System.out.println(space++ + ". ...");
      }
    }
  }

  public void showResult() {
    System.out.println("Canceled Request: " + resultCounter.getCanceledRequest());
    resultCounter.showResultDevice();
    resultCounter.showResultSource();
  }
}
