package ru.mirea.pizzaclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.pizzaclient.model.xml.Pizza;
import ru.mirea.pizzaclient.model.xml.Toppings;
import ru.mirea.pizzaclient.model.xml.User;

@Controller
@RequestMapping("/")
@SessionAttributes("user")
@RequiredArgsConstructor
public class PizzaController {
    private final PizzaService pizzaService;

    @ModelAttribute(name = "user")
    public User user() {return new User();}

    @GetMapping
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        String result = pizzaService.registerUser(user.getLogin());
        String greeting = result != null && result.endsWith("successfully registered!")? "thank you for registration!": "welcome back!";
        model.addAttribute("greeting", greeting);
        model.addAttribute("toppings", pizzaService.showToppings(user.getLogin()));
        model.addAttribute("savedPizzas", pizzaService.showPizza(user.getLogin()));
        model.addAttribute("orders", pizzaService.showOrder(user.getLogin()));
        model.addAttribute("pizza", new Pizza());
        return "dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @ModelAttribute User user){
        if(user.getLogin() == null) return "register";
        model.addAttribute("toppings", pizzaService.showToppings(user.getLogin()));
        model.addAttribute("savedPizzas", pizzaService.showPizza(user.getLogin()));
        model.addAttribute("orders", pizzaService.showOrder(user.getLogin()));
        return "dashboard";
    }

    @PostMapping("/makePizza")
    public String makePizza(@ModelAttribute Toppings toppings, @ModelAttribute Pizza pizza, @ModelAttribute User user, Model model) {
        pizza.setToppings(toppings);
        String result = pizzaService.makePizza(user.getLogin(), pizza);
        model.addAttribute("savedPizzas", pizzaService.showPizza(user.getLogin()));
        model.addAttribute("toppings", pizzaService.showToppings(user.getLogin()));
        return "dashboard";
    }

    @PostMapping("/makeOrder")
    public String makeOrder(@ModelAttribute Toppings toppings, @ModelAttribute Pizza pizza, @ModelAttribute User user, Model model) {
        pizza.setToppings(toppings);
        String result = pizzaService.makePizza(user.getLogin(), pizza);
        model.addAttribute("savedPizzas", pizzaService.showPizza(user.getLogin()));
        model.addAttribute("toppings", pizzaService.showToppings(user.getLogin()));
        return "dashboard";
    }
}
