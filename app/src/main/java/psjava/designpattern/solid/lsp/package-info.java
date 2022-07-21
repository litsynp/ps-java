/**
 * <h2>LSP</h2>
 *
 * <h3>Liskov Substitution Principle - 리스코프 치환 원칙</h3>
 * 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
 * <p>
 * 즉, LSP는 객체의 IS-A 관계를 만족하는 것이 아닌 객체의 행위의 IS-A를 만족해야 한다.
 * <p>
 * <h3>사각형 예제</h3>
 * 사각형의 넓이를 구하는 공식은 높이 곱하기 폭이다. 그리고 이는 일반적인 책임이다.
 * <p>
 * 하지만 정사각형의 넓이를 구하는 공식은 높이 곱하기 높이도 되고 폭 곱하기 폭도 되듯 높이와 폭의 차이가 없다.
 * <p>
 * 정사각형의 넓이를 구하는 공식은 사각형의 넓이를 구하는 공식은 본질적으로는 같으나 비본질적으론 다르다.
 *
 * @see <a href="https://bottom-to-top.tistory.com/27">https://bottom-to-top.tistory.com/27</a>
 */
package psjava.designpattern.solid.lsp;
