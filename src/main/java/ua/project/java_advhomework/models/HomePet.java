package ua.project.java_advhomework.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HomePet {
    private int id;
    private String name;
    private int age;
    private Owner owner;
    private Type type;
}
