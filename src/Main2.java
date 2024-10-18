import java.util.ArrayList;
import java.util.List;

public class Main2 {

    public interface IObserver {
        void update(String currency, double rate);
    }

    public interface ISubject {
        void addObserver(IObserver observer);
        void removeObserver(IObserver observer);
        void notifyObservers();
    }

    public static class CurrencyExchange implements ISubject {

        private List<IObserver> observers;
        private String currency;
        private double rate;

        public CurrencyExchange() {
            this.observers = new ArrayList<>();
        }

        public void setRate(String currency, double rate) {
            this.currency = currency;
            this.rate = rate;
            notifyObservers();
        }

        @Override
        public void addObserver(IObserver observer) {
            observers.add(observer);
        }

        @Override
        public void removeObserver(IObserver observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyObservers() {
            for (IObserver observer : observers) {
                observer.update(currency, rate);
            }
        }
    }

    public static class TraderObserver implements IObserver {

        private String name;

        public TraderObserver(String name) {
            this.name = name;
        }

        @Override
        public void update(String currency, double rate) {
            System.out.println("Трейдер " + name + ": курс валюты " + currency + " изменился до " + rate);
        }
    }

    public static class MobileAppObserver implements IObserver {

        private String appName;

        public MobileAppObserver(String appName) {
            this.appName = appName;
        }

        @Override
        public void update(String currency, double rate) {
            System.out.println("Мобильное приложение " + appName + ": обновлен курс валюты " + currency + " до " + rate);
        }
    }

    public static class NewsAgencyObserver implements IObserver {

        private String agencyName;

        public NewsAgencyObserver(String agencyName) {
            this.agencyName = agencyName;
        }

        @Override
        public void update(String currency, double rate) {
            System.out.println("Агентство новостей " + agencyName + ": курс валюты " + currency + " изменился до " + rate);
        }
    }

    public static void main(String[] args) {

        CurrencyExchange currencyExchange = new CurrencyExchange();

        TraderObserver trader1 = new TraderObserver("Иван");
        MobileAppObserver mobileApp = new MobileAppObserver("FinanceApp");
        NewsAgencyObserver newsAgency = new NewsAgencyObserver("Reuters");

        currencyExchange.addObserver(trader1);
        currencyExchange.addObserver(mobileApp);
        currencyExchange.addObserver(newsAgency);

        currencyExchange.setRate("USD", 75.5);
        System.out.println();

        currencyExchange.removeObserver(mobileApp);
        currencyExchange.setRate("EUR", 88.9);
    }
}
