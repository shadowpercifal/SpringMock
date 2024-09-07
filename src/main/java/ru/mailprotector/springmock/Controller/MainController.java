package ru.mailprotector.springmock.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mailprotector.springmock.Model.Currencies.BaseCurrency;
import ru.mailprotector.springmock.Model.RequestDTO;
import ru.mailprotector.springmock.Model.ResponseDTO;

import java.util.HashMap;

@RestController
public class MainController {
    public static final HashMap<String, BaseCurrency> currencies = new HashMap<String, BaseCurrency>(){{
        put("8", new BaseCurrency("USD", 2000));
        put("9", new BaseCurrency("EUR", 1000));
        put("0", new BaseCurrency("RUB", 10000));
    }};
    private Logger logger = LoggerFactory.getLogger(MainController.class);

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(
            value="info/postBalances",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> postBalances(@RequestBody RequestDTO request){
        try{
            logger.info("Processing request");
            logger.debug("********** Request Info **********\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request));
            if (request.getClientId().length() == 0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ClientId is empty!");
            }
            BaseCurrency currency = currencies.get(request.getClientId().charAt(0));
            currency = currency == null ? currencies.get("0") : currency;
            logger.info("Account currency: " + currency.name);
            final ResponseDTO response = new ResponseDTO(request.getRqUID(),
                    request.getClientId(),
                    request.getAccount(),
                    currency.name,
                    String.valueOf(Math.floor(Math.random() * currency.maxLimit)),
                    currency.maxLimit.toString());
            logger.info("Request passed");
            logger.debug("========== Response ==========\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
            return ResponseEntity.ok(response);
        }
        catch (Exception ex){
            logger.error("Request failed! Error: " + ex.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.toString());
        }
    }
}
