package com.company;

public class CarShop {
    private static final int totalConsumerThreads = 3;
    private static final int maximumCarSell = 3;
    private static final CarWarehouse carWarehouse = new CarWarehouse(maximumCarSell);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < totalConsumerThreads; i++) {
            new Thread(
                    null,
                    new CarBuyer(carWarehouse),
                    i + "")
                    .start();
        }
        new Thread(
                null,
                new CarSupplier(carWarehouse),
                "Toyota Motor SPB")
                .start();
    }
}