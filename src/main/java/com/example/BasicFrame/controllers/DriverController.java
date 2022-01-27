package com.example.BasicFrame.controllers;

import com.example.BasicFrame.dto.DoubleIntForm;
import com.example.BasicFrame.dto.Driver;
import com.example.BasicFrame.dto.Role;
import com.example.BasicFrame.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value ="/api/drivers")
public class DriverController {
    @Autowired
    DriverService driverService;


    @GetMapping()
    public ResponseEntity<List<Driver>> getDrivers(){
        return ResponseEntity.ok().body(driverService.getAllDrivers());
    }
    @GetMapping(value ="/{id}")
    public ResponseEntity<Driver> getDriver(@PathVariable int id){
        return ResponseEntity.ok().body(driverService.getDriver(id));
    }
    @PostMapping("/changes")
    public ResponseEntity<String> addDriver(@RequestBody Driver driver){
        return ResponseEntity.ok().body(driverService.addDriver(driver));
    }
    @PutMapping("/changes")
    public ResponseEntity<Driver> updateDriver(@RequestBody Driver driver){
         return ResponseEntity.ok().body(driverService.updateDriver(driver));
    }
    @DeleteMapping("/changes")
    public ResponseEntity<String> deleteDriver(@RequestBody int idDriver){
        return ResponseEntity.ok().body(driverService.deleteDriver(idDriver));
    }
    @PutMapping(value ="/roles")
    public ResponseEntity<String> addRoleToDriver(@RequestBody DoubleIntForm doubleIntForm){
        return ResponseEntity.ok().body(driverService.addRoleToDriver(doubleIntForm.getIdDriver(), doubleIntForm.getSecondId()));
    }
    @PostMapping(value ="/roles")
    public ResponseEntity<String> addRole(@RequestBody Role role){
        return ResponseEntity.ok().body(driverService.addRole(role));
    }
    @GetMapping(value ="/roles")
    public ResponseEntity<List<Role>> getRole(){
        return ResponseEntity.ok().body(driverService.getRoles());
    }


}
