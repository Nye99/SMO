package ru.spbstu.antufievsemen.courseworkSMO_2.archive;

import java.util.ArrayList;
import java.util.List;
import ru.spbstu.antufievsemen.courseworkSMO_2.source.request.Request;

public class BufferArchiveRequest {

  private List<Request> requestCancelList;

  public BufferArchiveRequest() {
    this.requestCancelList = new ArrayList<>();
  }

  public void writeCancelRequestArchive(Request request) {
    requestCancelList.add(request);
  }

  public void showInfoCanceledRequest() {
    if (requestCancelList.isEmpty()) {
      System.out.println("Canceled requests dont exist");
    } else {
      for (Request request : requestCancelList) {
        System.out.println(request.toString());
      }
    }
    System.out.println();
  }
}
