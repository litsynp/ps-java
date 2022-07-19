package psjava.designpattern.solid.ocp;

/**
 * OCP가 적용되지 않은 예제
 */
public class OcpBadEg {

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

    class ProductionValidator {
        public void validateProduction(Production production) throws IllegalAccessException {
            if (production.getOption().equals("N")) {
                if (production.getNameLength() < 3) {
                    throw new IllegalArgumentException("일반 상품의 이름은 3글자보다 길어야 합니다.");
                }
                // 추가 요구 사항에 따라 상품 조건 추가
            } else if (production.getOption().equals("E")) {
                if (production.getNameLength() < 10) {
                    throw new IllegalArgumentException("전자티켓 상품의 이름은 10글자보다 길어야 합니다.");
                }
            } else if (production.getOption().equals("L")) {
                if (production.getNameLength() < 20) {
                    throw new IllegalArgumentException("지역 상품의 이름은 20글자보다 길어야 합니다.");
                }
            }
        }
    }
}
