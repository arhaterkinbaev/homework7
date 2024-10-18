import java.util.Scanner;

public class Main1 {

    public interface IPaymentStrategy {
        void pay(double amount);
    }

    public static class CreditCardPayment implements IPaymentStrategy {

        private String cardNumber;

        public CreditCardPayment(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Оплата " + amount + " через банковскую карту " + cardNumber);
        }
    }

    public static class PayPalPayment implements IPaymentStrategy {

        private String email;

        public PayPalPayment(String email) {
            this.email = email;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Оплата " + amount + " через PayPal аккаунт " + email);
        }
    }

    public static class CryptoPayment implements IPaymentStrategy {

        private String walletAddress;

        public CryptoPayment(String walletAddress) {
            this.walletAddress = walletAddress;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Оплата " + amount + " криптовалютой на кошелек " + walletAddress);
        }
    }

    public static class PaymentContext {

        private IPaymentStrategy paymentStrategy;

        public void setPaymentStrategy(IPaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }

        public void executePayment(double amount) {
            if (paymentStrategy == null) {
                System.out.println("Стратегия оплаты не установлена!");
                return;
            }
            paymentStrategy.pay(amount);
        }
    }

    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите способ оплаты: 1 - Кредитная карта, 2 - PayPal, 3 - Криптовалюта");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Введите номер карты:");
                String cardNumber = scanner.next();
                paymentContext.setPaymentStrategy(new CreditCardPayment(cardNumber));
                break;
            case 2:
                System.out.println("Введите email PayPal:");
                String email = scanner.next();
                paymentContext.setPaymentStrategy(new PayPalPayment(email));
                break;
            case 3:
                System.out.println("Введите адрес крипто-кошелька:");
                String walletAddress = scanner.next();
                paymentContext.setPaymentStrategy(new CryptoPayment(walletAddress));
                break;
            default:
                System.out.println("Неверный выбор способа оплаты.");
                return;
        }

        System.out.println("Введите сумму для оплаты:");
        double amount = scanner.nextDouble();
        paymentContext.executePayment(amount);
    }
}
