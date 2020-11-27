package ru.spbstu.antufievsemen.courseworkSMO_2.counter;

import java.util.ArrayList;
import java.util.List;
import ru.spbstu.antufievsemen.courseworkSMO_2.source.request.Request;

public class SourceCounter {

  private List<Request> requestList;

  public SourceCounter() {
    requestList = new ArrayList<>();
  }

  public void readInfoRequest( Request request) {
    requestList.add(request);
  }
}
