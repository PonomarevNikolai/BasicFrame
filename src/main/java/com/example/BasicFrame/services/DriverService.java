package com.example.BasicFrame.services;

import com.example.BasicFrame.dto.Driver;
import com.example.BasicFrame.dto.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.BasicFrame.services.CarService.carsMap;

@Service
@RequiredArgsConstructor
public class DriverService implements UserDetailsService {

    protected static final Map<Integer, Driver> driversMap = new HashMap<>();
    private static final List <Role> rolesList = new ArrayList<>();



    static {
        initDrivers();
    }

    private static void initDrivers() {
        Role userRole = new Role(1, "USER");
        Role adminRole = new Role(1, "ADMIN");
        rolesList.add(userRole);
        rolesList.add(adminRole);
        Driver driver0 = new Driver(1, "admin@admin.ru", "BigBoss", "admin", new ArrayList<>(), new ArrayList<>());
        Driver driver = new Driver(2, "Jim@mail.ru", "Jim", "qwerty", new ArrayList<>(), new ArrayList<>());
        Driver driver2 = new Driver(3, "Lilit@mail.ru", "Lilit", "lips", new ArrayList<>(), new ArrayList<>());
        Driver driver3 = new Driver(4, "Frank@mail.ru", "Jim", "mercury", new ArrayList<>(), new ArrayList<>());
        driver0.getRoles().add(adminRole);
        driver.getRoles().add(userRole);
        driver2.getRoles().add(userRole);
        driver3.getRoles().add(userRole);
        driver.getCars().add(carsMap.get(3));
        driver2.getCars().add(carsMap.get(2));
        driver3.getCars().add(carsMap.get(1));
        driversMap.putIfAbsent(driver0.getId(), driver0);
        driversMap.putIfAbsent(driver.getId(), driver);
        driversMap.putIfAbsent(driver2.getId(), driver2);
        driversMap.putIfAbsent(driver3.getId(), driver3);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Driver driver = null;
        for (Map.Entry<Integer, Driver> d :
                driversMap.entrySet()) {
            if (d.getValue().getMail().equals(username)) {
                driver = d.getValue();
            }
        }
        try {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            rolesList.forEach(role->authorities.add(new SimpleGrantedAuthority(role.getName())));
            return new User(driver.getMail(),driver.getPassword(), authorities);

        }catch (NullPointerException e){
             e.toString();
        }
        return  new User("miss","miss",new ArrayList<>());
    }

    public String addRole(Role role) {
        rolesList.add(role);
        return "Создана роль:\n" + role.toString();
    }

    public String addRoleToDriver(int idDriver, int idRole) {
        Driver driver = driversMap.get(idDriver);
        driver.getRoles().add(rolesList.get(idRole));
        return "Водителю ID=" + idDriver + " добавлена роль ID=" + idRole;
    }

    public List<Role> getRoles() {
        return rolesList;
    }

    public Driver getDriver(int idDriver) {

        return driversMap.get(idDriver);
    }

    public String addDriver(Driver newDriver) {
        driversMap.putIfAbsent(newDriver.getId(), newDriver);
        return "Добавлен водитель:\n" + newDriver.toString();
    }


    public Driver updateDriver(Driver newDriver) {
        driversMap.put(newDriver.getId(), newDriver);
        return newDriver;
    }

    public List<Driver> getAllDrivers() {
        Collection<Driver> driverCollection = driversMap.values();
        List<Driver> usersList = new ArrayList<>();
        usersList.addAll(driverCollection);
        return usersList;

    }

    public String deleteDriver(int ID) {
        driversMap.remove(ID);
        return "Водитель ID=" + ID + " удален.";
    }


}
