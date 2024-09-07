package ru.mailprotector.springmock.Model.Currencies;

public class BaseCurrency {
    public String name;
    public Integer maxLimit;

    public BaseCurrency(String currencyName, int limit) {
        name = currencyName;
        maxLimit = limit;
    }
}
