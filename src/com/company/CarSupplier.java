package com.company;

public class CarSupplier implements Runnable {
    private final CarWarehouse carWarehouse;
    private final int carBuildTime = 2000;

    public CarSupplier(CarWarehouse carWarehouse) {
        this.carWarehouse = carWarehouse;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (carWarehouse) {
                if (!carWarehouse.isPossibleSell()) {
                    System.out.println("Производство машин остановлено. Достигнут лимит продаж = " + carWarehouse.totalCarSells());
                    carWarehouse.notifyAll();
                    break;
                }
                while (carWarehouse.isFull()) {
                    try {
                        carWarehouse.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(carBuildTime);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                carWarehouse.add(new Car("Toyota"));
                System.out.println("Производитель " + Thread.currentThread().getName() + ": выпустил 1 авто");
                carWarehouse.notifyAll();
            }
        }
    }
}
