package com.example.BasicFrame.services;

import com.example.BasicFrame.dto.Car;
import com.example.BasicFrame.dto.Driver;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarService {
    protected static final Map<Integer, Car> carsMap = new HashMap<>();

    static {
        initCars();
    }

    private static void initCars() {
        Car car = new Car(1, "Blue", "Crown");
        Car car2 = new Car(2, "Red", "Crown");
        Car car3 = new Car(3, "Grow", "Crown");
        car.setIdDriver(4);
        car2.setIdDriver(3);
        car3.setIdDriver(2);
        carsMap.putIfAbsent(car.getId(), car);
        carsMap.putIfAbsent(car2.getId(), car2);
        carsMap.putIfAbsent(car3.getId(), car3);
    }

    public String addCar(Car newCar) {
        if (carsMap.containsKey(newCar.getId())) {
            return "Машина с таким ID уже существует";
        }
        if (!DriverService.driversMap.containsKey(newCar.getIdDriver())) {
            return "Водитель с таким ID не существует, машина не добавлена";
        }
        Driver driver = DriverService.driversMap.get(newCar.getIdDriver());
        driver.getCars().add(newCar);
        DriverService.driversMap.put(driver.getId(), driver);
        carsMap.putIfAbsent(newCar.getId(), newCar);
        return "Машина добавленна:\n" + newCar.toString();
    }

    public String getCar(int carId) {
        if (!carsMap.containsKey(carId)) {
            return "Машина с таким ID не существует";
        }
        return carsMap.get(carId).toString();
    }


    public Car updateCar(Car newCar) {
        carsMap.put(newCar.getId(), newCar);
        return newCar;
    }

    public void deleteCar(int ID) {
        Car car = carsMap.get(ID);
        Driver driver = DriverService.driversMap.get(car.getIdDriver());
        driver.getCars().remove(car);
        DriverService.driversMap.put(driver.getId(), driver);
        carsMap.remove(ID);
    }

    public List<Car> getAllCars() {
        Collection<Car> carsCollection = carsMap.values();
        List<Car> carsList = new ArrayList<>();
        carsList.addAll(carsCollection);
        return carsList;
    }

    public String addCarToDriver(int idDriver, int carId) {
        Driver driver = DriverService.driversMap.get(idDriver);
        Car car = CarService.carsMap.get(carId);
        if (car.getIdDriver() != 0) {
            Driver driver2 = DriverService.driversMap.get(car.getIdDriver());
            driver2.getCars().add(car);
        }
        car.setIdDriver(idDriver);
        driver.getCars().add(car);
        DriverService.driversMap.put(driver.getId(), driver);
        return "Машина с ID=" + carId + " Добавленна водителю с ID=" + idDriver;
    }
}
