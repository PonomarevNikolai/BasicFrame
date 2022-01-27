package com.example.BasicFrame.controllers;

import com.example.BasicFrame.dto.Car;
import com.example.BasicFrame.dto.DoubleIntForm;
import com.example.BasicFrame.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping()
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping(value = "/{ID}")
    public String getCar(@PathVariable("ID") int ID) {
        return carService.getCar(ID);
    }

    @PostMapping()
    public String addCar(@RequestBody Car newCar) {
        return carService.addCar(newCar);
    }

    @PutMapping()
    public String updateCar(@RequestBody Car newCar) {
        carService.updateCar(newCar);
        return "Машина с ID=" + newCar.getId() + " изменен.";
    }

    @DeleteMapping(value = "/{ID}")
    public String deleteCar(@PathVariable("ID") int ID) {
        carService.deleteCar(ID);
        return "Машина с ID=" + ID + " удалена.";
    }

    @PutMapping("/change")
    public String addCarToDriver(@RequestBody DoubleIntForm intForm) {

        return carService.addCarToDriver(intForm.getIdDriver(), intForm.getSecondId());
    }

}
