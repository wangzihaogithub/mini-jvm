package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class LXOrInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    Long v2 = frame.popLong();
    Long v1 = frame.popLong();
    frame.pushLong(v1 ^ v2);
  }
}