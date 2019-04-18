package ru.mirea.pizzaclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzaChooser {
    private String pizzaName;
    private Integer count;
}
