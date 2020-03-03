package com.gxk.jvm.rtda;

import com.gxk.jvm.instruction.Instruction;
import com.gxk.jvm.rtda.heap.KMethod;

import java.util.Map;

public class Frame {

  public final KMethod method;
  private final LocalVars localVars;
  private final OperandStack operandStack;
  private final Map<Integer, Instruction> instructionMap;
  public final Thread thread;
  public int nextPc;
  private int pc;

  private Runnable onPop;

  public Frame(KMethod method, Thread thread) {
    this.method = method;
    //非静态方法的第一个凸是this. 所以计算的时候要自动+1.
    this.localVars = new LocalVars(method.isStatic()?method.maxLocals:method.maxLocals+1);
    this.operandStack = new OperandStack(method.maxStacks);
    this.thread = thread;
    this.instructionMap = method.instructionMap;
  }

  public Frame(KMethod method, LocalVars localVars, Thread thread) {
    this.method = method;
    this.localVars = localVars;
    this.operandStack = new OperandStack(method.maxStacks);
    this.thread = thread;
    this.instructionMap = method.instructionMap;
  }

  public Instruction getInst(int pc) {
    this.pc = pc;
    return this.instructionMap.get(pc);
  }

  public Runnable getOnPop() {
    return onPop;
  }

  public void setOnPop(Runnable onPop) {
    this.onPop = onPop;
  }

  // operand stack operation

  public void pushInt(Integer val) {
    this.operandStack.pushInt(val);
  }

  public Integer popInt() {
    return this.operandStack.popInt();
  }

  public void pushLong(Long val) {
    this.operandStack.pushLong(val);
  }

  public Long popLong() {
    return this.operandStack.popLong();
  }

  public void pushFloat(Float val) {
    this.operandStack.pushFloat(val);
  }

  public Float popFloat() {
    return this.operandStack.popFloat();
  }

  public void pushDouble(Double val) {
    this.operandStack.pushDouble(val);
  }

  public Double popDouble() {
    return this.operandStack.popDouble();
  }

  public void pushRef(Object val) {
    this.operandStack.pushRef(val);
  }

  public Object popRef() {
    return this.operandStack.popRef();
  }

  public Slot popSlot() {
    return this.operandStack.popSlot();
  }

  public void pushSlot(Slot val) {
    this.operandStack.pushSlot(val);
  }

  // local vars operation

  public void setInt(Integer index, Integer val) {
    this.localVars.setInt(index, val);
  }

  public Integer getInt(Integer index) {
    return this.localVars.getInt(index);
  }

  public void setFloat(Integer index, Float val) {
    this.localVars.setFloat(index, val);
  }

  public Float getFloat(Integer index) {
    return this.localVars.getFloat(index);
  }

  public Long getLong(Integer index) {
    return this.localVars.getLong(index);
  }

  public void setLong(Integer index, Long val) {
    this.localVars.setLong(index, val);
  }

  public void setDouble(int index, Double val) {
    this.localVars.setDouble(index, val);
  }

  public Double getDouble(int index) {
    return this.localVars.getDouble(index);
  }

  public void setRef(Integer index, Object ref) {
    this.localVars.setRef(index, ref);
  }

  public Object getRef(Integer index) {
    return this.localVars.getRef(index);
  }

  public LocalVars getLocalVars() {
    return this.localVars;
  }

  public OperandStack getOperandStack() {
    return this.operandStack;
  }

  public int getPc() {
    return pc;
  }

  public String debugNextPc(String space) {
    StringBuilder sb = new StringBuilder();
    sb.append(space + "nextPc = " + nextPc).append("\n");
    return sb.toString();
  }

  public String debugLocalVars(String space) {
    StringBuilder sb = new StringBuilder();
    sb.append(localVars.debug(space));
    return sb.append("\n").toString();
  }

  public String debugOperandStack(String space) {
    StringBuilder sb = new StringBuilder();
    sb.append(operandStack.debug(space));
    return sb.append("\n").toString();
  }

  public String getCurrentMethodFullName() {
    return this.method.clazz.name + "." + this.method.name;
  }

  public int getCurrentLine() {
    return this.method.getLine(this.pc);
  }

  public String getCurrentSource() {
    return this.method.clazz.getSource();
  }
}
