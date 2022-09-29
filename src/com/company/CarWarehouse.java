package com.company;

import java.util.ArrayList;

public class CarWarehouse {
    final static int maximumCarsInStock = 2;
    final static ArrayList<Car> carInStock = new ArrayList<>();
    private int totalCarSells = 0;
    private final int maximumCarSell;

    public CarWarehouse(int maximumCarSell) {
        this.maximumCarSell = maximumCarSell;
    }

    public Car getCar() {
        return carInStock.get(0);
    }

    public boolean inStock() {
        return carInStock.size() > 0;
    }

    public boolean isEmpty() {
        return carInStock.size() == 0;
    }

    public boolean isFull() {
        return carsInStock() == maximumCarsInStock;
    }
    public boolean isPossibleSell() {
        return totalCarSells() < maximumCarSell;
    }


    public int carsInStock() {
        return carInStock.size();
    }

    public void add(Car car) {
        if (!isFull()) {
            carInStock.add(car);
        }
    }

    public boolean sell() {
        if (isPossibleSell()) {
            if (inStock()) {
                totalCarSells++;
                carInStock.remove(0);
                return true;
            }
        }
        return false;
    }

    public int totalCarSells() {
        return totalCarSells;
    }
}
