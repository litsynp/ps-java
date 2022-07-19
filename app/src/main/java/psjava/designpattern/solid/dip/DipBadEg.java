package psjava.designpattern.solid.dip;

/**
 * DIP가 적용되지 않은 예제
 * <p>
 * Validator가 추가될 때마다 ProductionService가 갖는 의존성이 늘어난다.
 * <p>
 * Service 코드가 if...else if 가 늘어나게 되는 구조 - OCP에서 확인한 구조이다.
 */
public class DipBadEg {

    public static void main(String[] args) {
        ProductionV1 potato = new ProductionV1("포테이토", 1000, "L");
        ProductionV1 ticket = new ProductionV1("제주도행 티켓", 10000, "E");

        ProductionServiceV1 productionService = new ProductionServiceV1(
                new LocalValidatorV1(), new ETicketValidatorV1()
        );

        productionService.validate(potato);
        productionService.validate(ticket);
    }
}

class ProductionServiceV1 {

    // Validator가 추가될 때마다 ProductionService가 갖는 의존성이 늘어난다.
    private final LocalValidatorV1 localValidator;
    private final ETicketValidatorV1 eTicketValidator;
    // private final XXXValidator xxxValidator;
    // ...

    public ProductionServiceV1(LocalValidatorV1 localValidator, ETicketValidatorV1 eTicketValidator) {
        this.localValidator = localValidator;
        this.eTicketValidator = eTicketValidator;
    }

    public void validate(ProductionV1 production) {
        if (production.getType().equals("L")) {
            localValidator.validate(production);
        } else if (production.getType().equals("E")) {
            eTicketValidator.validate(production);
        }
    }
}

class LocalValidatorV1 {

    private static final int MINIMUM_PRICE = 1000;

    public void validate(ProductionV1 production) {
        // validate
        if (production.getPrice() < MINIMUM_PRICE) {
            throw new IllegalArgumentException(String.format("최소 가격은 %d원 이상입니다.", MINIMUM_PRICE));
        }
    }
}

class ETicketValidatorV1 {

    private static final int MINIMUM_NAME_LENGTH = 5;

    public void validate(ProductionV1 production) {
        // validate
        if (production.getPrice() < MINIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("이름의 길이는 최소 %d글자 이상입니다.", 5));
        }
    }
}

class ProductionV1 {

    private String name;
    private int price;
    private String type;

    public ProductionV1(String name, int price, String type) {
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
