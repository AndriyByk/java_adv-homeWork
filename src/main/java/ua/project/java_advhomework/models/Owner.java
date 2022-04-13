package ua.project.java_advhomework.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Owner {
    private int id;
    private String name;
    private boolean status;

}
