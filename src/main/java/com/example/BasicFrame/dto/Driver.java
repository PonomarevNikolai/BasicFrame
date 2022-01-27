package com.example.BasicFrame.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    private int id;
    private String mail;
    private String name;
    private String password;
    private Collection<Role> roles = new ArrayList<>();
    private List<Car> cars=new ArrayList<>();
}
