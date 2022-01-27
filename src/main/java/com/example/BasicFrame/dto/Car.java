package com.example.BasicFrame.dto;

import lombok.Data;

@Data
public class Car {
    private int id;
    private String color;
    private String type;
    private int idDriver=0;

    public Car(int id, String color, String type) {
        this.id = id;
        this.color = color;
        this.type = type;
    }
}
