package psjava.designpattern.solid.ocp;

import java.util.*;

public class OcpEg {

    public static void main(String[] args) {
        Production ipad = new Production("아이패드", 1000, "N");
        Production ticket = new Production("제주도행 티켓", 10000, "E");
        Production potato = new Production("포테토칩", 500, "L");

        ProductValidator validator = new ProductValidator();
        validator.validate(ipad);
        validator.validate(ticket);
        validator.validate(potato);
    }
}

class Production {

    private String name;
    private int price;

    // N(일반) ,E(전자티켓) ,L(지역상품)...
    private String option;

    public Production(String name, int price, String option) {
        this.name = name;
        this.price = price;
        this.option = option;
    }

    public int getNameLength() {
        return name.length();
    }

    public String getOption() {
        return option;
    }
}

class ProductValidator {

    private final List<Validator> validators = Arrays.asList(new DefaultValidator(), new ETicketValidator(), new LocalValidator());

    public void validate(Production production) {
        Validator productionValidator = new DefaultValidator();

        // 새로운 옵션이 생성되어 검증 로직이 추가되야 할 때
        // OCP를 지키지 않은 구조와 달리
        // ProductValidator 의 validate() 의 수정 없이
        // 해당 검증을 담당할 객체를 추가하여 요구사항을 충족시킬 수 있다
        for (Validator localValidator : validators) {
            if (localValidator.support(production)) {
                productionValidator = localValidator;
                break;
            }
        }

        // 코드의 변경 없이 확장 가능!
        productionValidator.validate(production);
    }
}

interface Validator {

    boolean support(Production production);

    void validate(Production production) throws IllegalArgumentException;
}

class DefaultValidator implements Validator {

    private static final int NAME_LENGTH_LIMIT = 3;

    @Override
    public boolean support(Production production) {
        return production.getOption().equals("N");
    }

    @Override
    public void validate(Production production) throws IllegalArgumentException {
        if (production.getNameLength() < NAME_LENGTH_LIMIT) {
            throw new IllegalArgumentException(String.format("일반 상품의 이름은 %d글자보다 길어야 합니다.", NAME_LENGTH_LIMIT));
        }
    }
}

class ETicketValidator implements Validator {

    private static final int NAME_LENGTH_LIMIT = 5;

    @Override
    public boolean support(Production production) {
        return production.getOption().equals("E");
    }

    @Override
    public void validate(Production production) throws IllegalArgumentException {
        if (production.getNameLength() < NAME_LENGTH_LIMIT) {
            throw new IllegalArgumentException(String.format("전자티켓 상품의 이름은 %d글자보다 길어야 합니다.", NAME_LENGTH_LIMIT));
        }
    }
}

class LocalValidator implements Validator {

    private static final int NAME_LENGTH_LIMIT = 4;

    @Override
    public boolean support(Production production) {
        return production.getOption().equals("L");
    }

    @Override
    public void validate(Production production) throws IllegalArgumentException {
        if (production.getNameLength() < NAME_LENGTH_LIMIT) {
            throw new IllegalArgumentException(String.format("지역 상품의 이름은 %d글자보다 길어야 합니다.", NAME_LENGTH_LIMIT));
        }
    }
}
