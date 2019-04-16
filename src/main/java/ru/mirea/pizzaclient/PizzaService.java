package ru.mirea.pizzaclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.pizzaclient.model.xml.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaService {
    private final PizzaClient pizzaClient;

    public String registerUser(String userl) {
        return pizzaClient.registerUser(new RegisterUserRequest(){{setUser(new User(){{setLogin(userl);}});}}).getResult();
    }

    public List<Topping> showToppings(String userl) {
        return pizzaClient.showIngridients(new ShowIngridientsRequest(){{setUser(new User(){{setLogin(userl);}});}}).getIngridient();
    }

    public String makePizza(String userl, Pizza pizzal) {
        return pizzaClient.makePizza(new MakePizzaRequest(){{setPizza(pizzal); setUser(new User(){{setLogin(userl);}});}}).getResult();
    }

    public List<Pizza> showPizza(String  userl) {
        return pizzaClient.showPizza(new ShowPizzaRequest(){{setUser(new User(){{setLogin(userl);}});}}).getPizza();
    }

    public String makeOrder(Order orderl) {
        return pizzaClient.makeOrder(new MakeOrderRequest(){{setOrder(orderl);}}).getResult();
    }

    public List<Order> showOrder(String userl) {
        return pizzaClient.showOrder(new ShowOrderRequest(){{setUser(new User(){{setLogin(userl);}});}}).getOrder();
    }
}
