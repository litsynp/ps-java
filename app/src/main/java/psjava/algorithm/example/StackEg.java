package psjava.algorithm.example;

import java.io.*;
import java.util.*;

/**
 * Stack<E>
 *
 * - 순차적으로 데이터를 접근하면서, 이전 데이터와 신규 데이터가 같을 때 연산이 이루어지는 문제에서 사용
 *
 * - 중복 허용한다.
 */
public class StackEg {
  public static void main(String[] args) throws IOException {

    // Stack 생성
    Stack<Integer> stk = new Stack<>();

    // Stack이 비어있다면
    if (stk.empty()) {
      // Stack에 1, 2, 3 삽입
      stk.push(1);
      stk.push(2);
      stk.push(3);
    }

    // Stack이 비어있지 않다면
    if (!stk.empty()) {
      // Stack의 최상단에 3이 있다면
      if (stk.peek() == 3) {
        // Stack에서 추출
        stk.pop();
      }
    }

    // Stack 요소 중 3이 존재하지 않는다면
    if (stk.search(3) == -1) {
      System.out.println("3 is popped");
    }
  }
}
