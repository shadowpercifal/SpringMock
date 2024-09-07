package ru.mailprotector.springmock.Controller;

import org.springframework.web.bind.annotation.*;
import ru.mailprotector.springmock.Model.Currencies.BaseCurrency;
import ru.mailprotector.springmock.Model.RequestDTO;
import ru.mailprotector.springmock.Model.ResponseDTO;

import java.util.HashMap;

@RestController
@RequestMapping("/account")
public class AccountInfoController {

    public static final HashMap<String, BaseCurrency> currencies = new HashMap<String, BaseCurrency>(){{
        put("8", new BaseCurrency("USD", 2000));
        put("9", new BaseCurrency("EUR", 1000));
        put("0", new BaseCurrency("RUB", 10000));
    }};
    @GetMapping("/info")
    public ResponseDTO info(@RequestBody RequestDTO request) {

        final ResponseDTO response = new ResponseDTO(request);
        if (request.getClientId().length() == 0){
            return null;
        }
        BaseCurrency currency = currencies.get(request.getClientId().charAt(0));
        currency = currency == null ? currencies.get("0") : currency;
        response.setCurrency(currency.name);
        response.setMaxLimit(currency.maxLimit.toString());
        response.setBalance(String.valueOf(Math.floor(Math.random() * currency.maxLimit)));
        return response;
    }
}