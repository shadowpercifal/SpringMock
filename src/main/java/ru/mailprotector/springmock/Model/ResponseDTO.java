package ru.mailprotector.springmock.Model;

import lombok.Data;

@Data
public class ResponseDTO {
    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private String balance;
    private String maxLimit;


    public ResponseDTO(RequestDTO request){
        rqUID=request.getRqUID();
        clientId=request.getClientId();
        account=request.getAccount();
    }

    public ResponseDTO(String rqUID, String clientId, String account, String currency, String balance, String maxLimit) {
        this.rqUID = rqUID;
        this.clientId = clientId;
        this.account = account;
        this.currency = currency;
        this.balance = balance;
        this.maxLimit = maxLimit;
    }
}
