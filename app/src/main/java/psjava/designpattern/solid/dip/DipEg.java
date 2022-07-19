package psjava.designpattern.solid.dip;

/**
 * DIP가 적용된 예제
 * <p>
 * Validator가 추가되어도 ProductionService가 갖는 의존성이 늘어나지 않는다.
 * <p>
 * Service 코드가 if...else if 구조가 되지 않고 생성자에 전달한 validator에 따라 실행하게 된다.
 */
public class DipEg {

    public static void main(String[] args) {
        Production potato = new Production("포테이토", 1000, "L");
        Production ticket = new Production("제주도행 티켓", 10000, "E");

        ProductionService localService = new ProductionService(new LocalValidator());
        localService.validate(potato);

        ProductionService eTicketService = new ProductionService(new ETicketValidator());
        eTicketService.validate(ticket);
    }
}

class ProductionService {

    private final Validator validator;

    public ProductionService(Validator validator) {
        this.validator = validator;
    }

    public void validate(Production production) {
        validator.validate(production);
    }
}


interface Validator {
    void validate(Production production);
}

class LocalValidator implements Validator {

    private static final int MINIMUM_PRICE = 1000;

    @Override
    public void validate(Production production) {
        // validate
        if (production.getPrice() < MINIMUM_PRICE) {
            throw new IllegalArgumentException(String.format("최소 가격은 %d원 이상입니다.", MINIMUM_PRICE));
        }
    }
}

class ETicketValidator implements Validator {

    private static final int MINIMUM_NAME_LENGTH = 5;

    @Override
    public void validate(Production production) {
        // validate
        if (production.getPrice() < MINIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("이름의 길이는 최소 %d글자 이상입니다.", 5));
        }
    }
}

class Production {

    private String name;
    private int price;
    private String type;

    public Production(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public void updatePrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Production{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }
}
