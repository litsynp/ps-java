package psjava.designpattern.solid.srp;

/**
 * SRP가 지켜진 코드
 */
public class SrpEg {

    public static void main(String[] args) {
        Production production = new Production("아령", 1000);
        System.out.println("production = " + production);

        ProductionUpdateService service = new ProductionUpdateService();
        service.update(production, 2000);
        System.out.println("production = " + production);
    }

    static class Production {

        private String name;
        private int price;
        private static final int MINIMUM_PRICE = 1000;

        public Production(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public void updatePrice(int price) {
            validatePrice(price);
            this.price = price;
        }

        private void validatePrice(int price) {
            if (price < MINIMUM_PRICE) {
                throw new IllegalArgumentException(String.format("최소 가격은 %d원 이상입니다.", MINIMUM_PRICE));
            }
        }

        @Override
        public String toString() {
            return "Production{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    static class ProductionUpdateService {

        public void update(Production production, int price) {
            // update price
            production.updatePrice(price);
        }
    }
}
