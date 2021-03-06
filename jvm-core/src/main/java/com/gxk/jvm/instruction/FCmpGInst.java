package com.gxk.jvm.instruction;

import com.gxk.jvm.rtda.Frame;

public class FCmpGInst implements Instruction {

  @Override
  public void execute(Frame frame) {
    float v2 = frame.popFloat();
    float v1 = frame.popFloat();
    if (v1 == v2) {
      frame.pushInt(0);
      return;
    }
    if (v1 < v2) {
      frame.pushInt(-1);
      return;
    }
    frame.pushInt(1);
  }
}