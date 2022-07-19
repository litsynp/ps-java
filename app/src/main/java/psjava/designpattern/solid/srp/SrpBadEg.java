package psjava.designpattern.solid.srp;

/**
 * SRP가 지켜지지 않은 코드
 */
public class SrpBadEg {

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

        public Production(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public void updatePrice(int price) {
            this.price = price;
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
            // validate price
            validatePrice(price);

            // update price
            production.updatePrice(price);
        }

        private void validatePrice(int price) {
            if (price < 1000) {
                throw new IllegalArgumentException("최소 가격은 1000원 이상입니다.");
            }
        }
    }
}
