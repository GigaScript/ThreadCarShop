package com.company;

public class CarBuyer implements Runnable {
    private final CarWarehouse carWarehouse;
    private final int thinkBuyerTime = 1000;

    public CarBuyer(CarWarehouse carWarehouse) {
        this.carWarehouse = carWarehouse;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (carWarehouse) {
                System.out.println("Покупатель" + Thread.currentThread().getName() + ": зашел в автосалон");
                while (carWarehouse.isEmpty()) {
                    try {

                        System.out.println("Машин нет");
                        carWarehouse.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    if (!carWarehouse.isPossibleSell()) {
                        System.out.println("Покупатель" + Thread.currentThread().getName() + " не купил, достигнут лимит = " + carWarehouse.totalCarSells());
                        carWarehouse.notifyAll();
                        break;
                    }
                    Thread.sleep(thinkBuyerTime);
                    if (carWarehouse.inStock()) {
                        System.out.println("Покупатель" + Thread.currentThread().getName() + ": уехал на новеньком авто: " + carWarehouse.getCar().getManufacturer());
                        carWarehouse.sell();
                        carWarehouse.notifyAll();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
