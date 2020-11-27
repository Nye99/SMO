package ru.spbstu.antufievsemen.courseworkSMO_2.source;

import java.util.ArrayList;
import java.util.List;
import ru.spbstu.antufievsemen.courseworkSMO_2.buffer.Buffer;
import ru.spbstu.antufievsemen.courseworkSMO_2.counter.SourceCounter;

public class SourceBuffer {

  private int size;
  private int constraint;
  private Buffer buffer;
  private SourceCounter sourceCounter;
  private List<Source> sourceList;


  public SourceBuffer(int size, int constraint, Buffer buffer, SourceCounter sourceCounter) {
    this.size = size;
    this.constraint = constraint;
    this.buffer = buffer;
    this.sourceCounter = sourceCounter;
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
      Source source = new Source(i, constraint, buffer, sourceCounter);
      sourceList.add(source);
    }
    sourceList.forEach(Thread::start);
  }
}
