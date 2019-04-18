package ru.mirea.pizzaclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.pizzaclient.model.xml.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
@SessionAttributes({"user", "orders", "savedPizzas", "order", "greeting"})
@RequiredArgsConstructor
public class PizzaController {
    private final PizzaService pizzaService;

    @ModelAttribute(name = "user")
    public User user() {return new User();}
    @ModelAttribute(name = "savedPizzas")
    public List<Pizza> savedPizzas() {return Collections.EMPTY_LIST;}
    @ModelAttribute(name = "orders")
    public List<Order> orders() {return Collections.EMPTY_LIST;}
    @ModelAttribute(name = "toppings")
    public List<Topping> showToppings() {return Collections.EMPTY_LIST;}
    @ModelAttribute(name = "order")
    public Order order() {return new Order();}
    @ModelAttribute(name = "pizzaChooser")
    public PizzaChooser pizzaChooser() {return new PizzaChooser();}
    @ModelAttribute(name = "pizza")
    public Pizza pizza() {return new Pizza();}

    private List<Pizza> pizzas;

    @GetMapping
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        String result = pizzaService.registerUser(user.getLogin());
        String greeting = result != null && result.endsWith("successfully registered!")? "cпасибо за регистрацию!": "c возвращением!";
        model.addAttribute("greeting", greeting);
        model.addAttribute("toppings", pizzaService.showToppings(user.getLogin()));
        model.addAttribute("savedPizzas", pizzaService.showPizza(user.getLogin()));
        model.addAttribute("orders", pizzaService.showOrder(user.getLogin()));
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("order", new Order());
        return "dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @ModelAttribute User user){
        if(user.getLogin() == null) return "register";
        model.addAttribute("toppings", pizzaService.showToppings(user.getLogin()));
        model.addAttribute("savedPizzas", pizzaService.showPizza(user.getLogin()));
        model.addAttribute("orders", pizzaService.showOrder(user.getLogin()));
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("order", new Order());
        return "dashboard";
    }

    @PostMapping("/makePizza")
    public String makePizza(@ModelAttribute Toppings toppings, @ModelAttribute Pizza pizza, @ModelAttribute User user, Model model) {
        pizza.setToppings(toppings);
        String result = pizzaService.makePizza(user.getLogin(), pizza);
        model.addAttribute("savedPizzas", pizzaService.showPizza(user.getLogin()));
        model.addAttribute("toppings", pizzaService.showToppings(user.getLogin()));
        model.addAttribute("orders", pizzaService.showOrder(user.getLogin()));
        return "dashboard";
    }

    @PostMapping(value = "/makeOrder", params="action=submit")
    public String makeOrder(@ModelAttribute Order order, @ModelAttribute("savedPizzas") List<Pizza> savedPizzas, @ModelAttribute User user, Model model) {
        order.setUser(user);
        String result = pizzaService.makeOrder(order);
        model.addAttribute("savedPizzas", savedPizzas);
        model.addAttribute("order", new Order());
        model.addAttribute("orders", pizzaService.showOrder(user.getLogin()));
        model.addAttribute("toppings", pizzaService.showToppings(user.getLogin()));
        model.addAttribute("savedPizzas", pizzaService.showPizza(user.getLogin()));
        return "dashboard";
    }

    @PostMapping(value = "/makeOrder", params="action=update")
    public String makeOrder(@ModelAttribute Order order, @ModelAttribute("savedPizzas") List<Pizza> savedPizzas, @ModelAttribute PizzaChooser pizzaChooser,@ModelAttribute User user,  Model model) {
        List<Pizza> available = savedPizzas;
        available.stream()
                .filter(p->pizzaChooser.getPizzaName().equals(p.getName())).findFirst()
                .ifPresent(p-> IntStream.range(0, Optional.ofNullable(pizzaChooser.getCount()).orElse(1)).mapToObj(i->p)
                        .forEach(order.getPizza()::add));
        model.addAttribute("savedPizzas", savedPizzas);
        model.addAttribute("toppings", pizzaService.showToppings(user.getLogin()));
        model.addAttribute("orders", pizzaService.showOrder(user.getLogin()));
        return "dashboard";
    }
}
