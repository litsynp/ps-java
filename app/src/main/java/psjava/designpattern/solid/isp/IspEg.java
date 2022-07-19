package psjava.designpattern.solid.isp;

/**
 * ISP가 적용된 예제
 */
public class IspEg {

    public static void main(String[] args) {
        SmartMachine smartMachine = new SmartMachine();
        smartMachine.print();
        smartMachine.copy();
        smartMachine.fax();

        // 특정 기능만 클라이언트에게 노출 가능
        PrinterDevice printer = new SmartMachine();
        printer.print();
    }
}

interface PrinterDevice {

    void print();
}

interface CopyDevice {

    void copy();
}

interface FaxDevice {

    void fax();
}

/**
 * 복합기가 필요하다면 다음과 같이 3개의 인터페이스를 전부 구현
 * <p>
 * 마찬가지로 특정 기능만을 필요로 하는 객체가 있다면
 * <p>
 * - 필요한 인터페이스만 이용하여 구현한다.
 * <p>
 * - SmartMachine 을 해당 인터페이스로 업캐스팅한다.
 */
class SmartMachine implements PrinterDevice, CopyDevice, FaxDevice {

    @Override
    public void print() {
        System.out.println("print");
    }

    @Override
    public void copy() {
        System.out.println("copy");
    }

    @Override
    public void fax() {
        System.out.println("fax");
    }
}

/**
 * 필요한 기능만 구현
 */
class PrinterMachine implements PrinterDevice {

    @Override
    public void print() {
        System.out.println("print");
    }
}
