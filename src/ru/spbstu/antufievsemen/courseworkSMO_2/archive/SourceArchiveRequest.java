package ru.spbstu.antufievsemen.courseworkSMO_2.archive;

import java.util.ArrayList;
import java.util.List;
import ru.spbstu.antufievsemen.courseworkSMO_2.source.request.Request;

public class SourceArchiveRequest {

  private List<Request> requestList;

  public SourceArchiveRequest() {
    requestList = new ArrayList<>();
  }

  public void writeRequestArchive(Request request) {
    requestList.add(request);
  }

  public void showInfoRequest() {
    if (!requestList.isEmpty()) {
      for (Request request : requestList) {
        System.out.println(request.toString());
      }
      System.out.println();
    }
  }
}
