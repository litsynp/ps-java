package psjava.designpattern.solid.isp;

/**
 * ISP가 적용되지 않은 예제
 */
public class IspBadEg {

    public static void main(String[] args) {
        SmartMachineV1 smartMachine = new SmartMachineV1();
        smartMachine.print();
        smartMachine.copy();
        smartMachine.fax();

        PrinterMachineV1 printer = new PrinterMachineV1();

        printer.print();
        printer.copy();
        printer.fax();
    }
}

/**
 * 여러 기능을 가진 복합기
 */
interface AllInOneDevice {

    void print();

    void copy();

    void fax();
}

class SmartMachineV1 implements AllInOneDevice {

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
 * 인쇄기
 * <p>
 * 인쇄 기능만 있으면 되는데 copy, fax까지 구현해야 함
 * <p>
 * - 인터페이스만 알고 있는 클라이언트는 printer에서 copy기능이 구현되어 있는지 안되어있는지 모르기 때문에 예상치 못한 오류를 만날 수 있음
 * <p>
 * - 자신의 책임이 아닌 기능인 copy, fax 까지 갖고 있으므로, SRP에도 어긋남
 * <p>
 * => 해결책은 ISP의 이름에도 나와 있듯 하나의 인터페이스를 분리하여 여러개의 인터페이스로 나누는 것!
 */
class PrinterMachineV1 implements AllInOneDevice {

    @Override
    public void print() {
        System.out.println("print");
    }

    @Override
    public void copy() {
        // 구현할 필요가 없어서 exception
        throw new UnsupportedOperationException();
    }

    @Override
    public void fax() {
        // 구현할 필요가 없어서 exception
        throw new UnsupportedOperationException();
    }
}
