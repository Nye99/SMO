package ru.antufiev.courseworkSMO.source;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import ru.antufiev.courseworkSMO.buffer.Buffer;
import ru.antufiev.courseworkSMO.globaltime.GlobalTimer;
import ru.antufiev.courseworkSMO.resultcounter.ResultCounter;

public class SourceBuffer extends Thread {

  public static AtomicInteger requestNumber = new AtomicInteger(0);
  private int size;
  private Buffer buffer;
  private GlobalTimer globalTimer;
  private ResultCounter resultCounter;
  private List<Thread> sourceBuffer;

  public SourceBuffer(int size, Buffer buffer, GlobalTimer globalTimer, ResultCounter resultCounter) {
    this.size = size;
    this.buffer = buffer;
    this.globalTimer = globalTimer;
    this.resultCounter = resultCounter;
  }

  public boolean isOver() {
    for (Thread thread : sourceBuffer) {
      if (!thread.isAlive()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void run() {
    sourceBuffer = new ArrayList<>();
      for (int i = 0; i < size; i++) {
      Thread thread = new Thread(new Source(i, buffer, globalTimer, resultCounter));
      thread.setPriority(10 - i);
      sourceBuffer.add(thread);
    }
    sourceBuffer.forEach(Thread::start);
  }
}
