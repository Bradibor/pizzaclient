package ru.mirea.pizzaclient;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.mirea.pizzaclient.model.xml.Topping;

@Component
public class ToppingConverter implements Converter<String, Topping> {

    @Override
    public Topping convert(String s) {
        return new Topping(){{setName(s);}};
    }
}
