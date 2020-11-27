package ru.spbstu.antufievsemen.courseworkSMO_2;

import ru.spbstu.antufievsemen.courseworkSMO_2.Device.DeviceBuffer;
import ru.spbstu.antufievsemen.courseworkSMO_2.buffer.Buffer;
import ru.spbstu.antufievsemen.courseworkSMO_2.counter.DeviceCounter;
import ru.spbstu.antufievsemen.courseworkSMO_2.counter.SourceCounter;
import ru.spbstu.antufievsemen.courseworkSMO_2.globaltime.GlobalTime;
import ru.spbstu.antufievsemen.courseworkSMO_2.source.SourceBuffer;

public class Main {
  public static void main(String[] args) {
    GlobalTime globalTime = new GlobalTime();
    Buffer buffer = new Buffer(3);
    SourceCounter sourceCounter = new SourceCounter();
    DeviceCounter deviceCounter = new DeviceCounter();
    SourceBuffer sourceBuffer = new SourceBuffer(2, 5, buffer, sourceCounter);
    DeviceBuffer deviceBuffer = new DeviceBuffer(3,  sourceBuffer, buffer, deviceCounter);
    sourceBuffer.start();
    deviceBuffer.start();
  }
}
