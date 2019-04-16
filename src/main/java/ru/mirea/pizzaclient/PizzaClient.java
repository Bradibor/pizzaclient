package ru.mirea.pizzaclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import ru.mirea.pizzaclient.model.xml.*;

public class PizzaClient extends WebServiceGatewaySupport {

    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        return (RegisterUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/pizza", request,
                        new SoapActionCallback("http://mirea.ru/pizza/RegisterUserRequest"));
    }

    public ShowIngridientsResponse showIngridients(ShowIngridientsRequest request) {
        return (ShowIngridientsResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/pizza", request,
                        new SoapActionCallback("http://mirea.ru/pizza/ShowIngridientsRequest"));
    }

    public MakePizzaResponse makePizza(MakePizzaRequest request) {
        return (MakePizzaResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/pizza", request,
                        new SoapActionCallback("http://mirea.ru/pizza/MakePizzaRequest"));
    }

    public ShowPizzaResponse showPizza(ShowPizzaRequest request) {
        return (ShowPizzaResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/pizza", request,
                        new SoapActionCallback("http://mirea.ru/pizza/ShowPizzaRequest"));
    }

    public MakeOrderResponse makeOrder(MakeOrderRequest request) {
        return (MakeOrderResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/pizza", request,
                        new SoapActionCallback("http://mirea.ru/pizza/MakeOrderRequest"));
    }

    public ShowOrderResponse showOrder(ShowOrderRequest request) {
        return (ShowOrderResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/pizza", request,
                        new SoapActionCallback("http://mirea.ru/pizza/ShowOrderRequest"));
    }
}
