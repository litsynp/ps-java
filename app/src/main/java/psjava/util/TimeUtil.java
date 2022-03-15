package psjava.util;

import java.time.*;

/**
 * 시간초 (ms)를 재는데 사용하는 클래스
 */
public class TimeUtil {

  private Instant start;
  private Instant end;

  public void start() {
    start = Instant.now();
  }

  public void end() {
    end = Instant.now();
  }

  public long getDurationMs() {
    return Duration.between(start, end).toMillis();
  }

  public void printDurationMs() {
    System.out.println("Took " + getDurationMs() + " ms");
  }
}
