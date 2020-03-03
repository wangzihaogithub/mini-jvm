package com.gxk.jvm.util;

public abstract class EnvHolder {

  public static String PATH_SEPARATOR = ":";
  public static String FILE_SEPARATOR = "/";

  public static boolean verbose;
  public static boolean verboseTrace;
  public static boolean verboseCall;
  public static boolean verboseClass;
  public static boolean verboseDebug;
  public static boolean debug;

  static {
      //我是window电脑, 要打开这个
    init();
  }

  public static void init() {
    PATH_SEPARATOR = isWindows() ? ";" : ":";
    FILE_SEPARATOR = isWindows() ? "\\" : "/";
  }

  public static boolean isWindows() {
    String osName = System.getProperty("os.name").toUpperCase();
    return osName.startsWith("WINDOWS");
  }
}
