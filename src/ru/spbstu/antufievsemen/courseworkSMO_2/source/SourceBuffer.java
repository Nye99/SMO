package ru.spbstu.antufievsemen.courseworkSMO_2.source;

import java.util.ArrayList;
import java.util.List;
import ru.spbstu.antufievsemen.courseworkSMO_2.archive.SourceArchiveRequest;
import ru.spbstu.antufievsemen.courseworkSMO_2.buffer.Buffer;

public class SourceBuffer {

  private int size;
  private int constraint;
  private Buffer buffer;
  private SourceArchiveRequest sourceArchiveRequest;
  private List<Source> sourceList;


  public SourceBuffer(int size, int constraint, Buffer buffer, SourceArchiveRequest sourceArchiveRequest) {
    this.size = size;
    this.constraint = constraint;
    this.buffer = buffer;
    this.sourceArchiveRequest = sourceArchiveRequest;
    sourceList = new ArrayList<>();
  }

  public boolean isAlive() {
    for (Source source : sourceList) {
      if (source.isAlive()) {
        return true;
      }
    }
    return false;
  }

  public void start() {
    for (int i = 0; i < size; i++) {
      Source source = new Source(i, constraint, buffer, sourceArchiveRequest);
      sourceList.add(source);
    }
    sourceList.forEach(Thread::start);
  }
}
