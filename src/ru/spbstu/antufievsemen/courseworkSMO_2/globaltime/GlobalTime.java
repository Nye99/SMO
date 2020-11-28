package ru.spbstu.antufievsemen.courseworkSMO_2.globaltime;

import java.util.concurrent.atomic.AtomicInteger;

public final class GlobalTime {

  private static Long START_TIME;

  private GlobalTime() {
  }

  /**
   * В первое обращение возвращает 0, как старт отсчета. В последующие возвращает стартовое значяение + дельта.
   *
   * @return
   */
  public static long startOrGetTime() {
    long currentTimeMillis = System.currentTimeMillis();
    if (START_TIME == null) {
      START_TIME = currentTimeMillis;
    }
    return currentTimeMillis - START_TIME;
  }


}
