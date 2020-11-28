package ru.spbstu.antufievsemen.courseworkSMO_2;

import ru.spbstu.antufievsemen.courseworkSMO_2.Device.DeviceBuffer;
import ru.spbstu.antufievsemen.courseworkSMO_2.archive.BufferArchiveRequest;
import ru.spbstu.antufievsemen.courseworkSMO_2.archive.DeviceArchiveRequest;
import ru.spbstu.antufievsemen.courseworkSMO_2.archive.SourceArchiveRequest;
import ru.spbstu.antufievsemen.courseworkSMO_2.buffer.Buffer;
import ru.spbstu.antufievsemen.courseworkSMO_2.source.SourceBuffer;

public class Main {
  public static void main(String[] args) {
    SourceArchiveRequest sourceArchiveRequest = new SourceArchiveRequest();
    DeviceArchiveRequest deviceArchiveRequest = new DeviceArchiveRequest();
    BufferArchiveRequest bufferArchiveRequest = new BufferArchiveRequest();
    Buffer buffer = new Buffer(3, bufferArchiveRequest);
    SourceBuffer sourceBuffer = new SourceBuffer(2, 5, buffer, sourceArchiveRequest);
    DeviceBuffer deviceBuffer = new DeviceBuffer(3,  sourceBuffer, buffer, deviceArchiveRequest);
    sourceBuffer.start();
    deviceBuffer.start();
    while(deviceBuffer.isAlive()) {
    }
    deviceArchiveRequest.showInfo();
    sourceArchiveRequest.showInfoRequest();
    bufferArchiveRequest.showInfoCanceledRequest();
  }
}
