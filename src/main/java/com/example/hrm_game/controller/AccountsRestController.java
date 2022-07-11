package com.example.hrm_game.controller;

import com.example.hrm_game.data.dto.AccountDto;
import com.example.hrm_game.data.entity.UserEntity;
import com.example.hrm_game.service.AccountService;
import com.example.hrm_game.service.UserService;
import com.example.hrm_game.service.utils.JacksonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

import static com.example.hrm_game.data.URLConst.API;
import static com.example.hrm_game.data.URLConst.HOST;

@RestController
public class AccountsRestController {
    @Autowired
    private AccountService accountsService;
    ObjectMapper mapper = new ObjectMapper();
    StringBuilder uri = new StringBuilder().append(HOST).append(API);
    private final static String TOKEN = "Token 3ed364ddfe4bb9817ee7328b64b1fcb9acf09407";

//    http://178.154.246.238:58082/api/accounts + получить токен через метод /login/ (сначала его получаю, потом подставляю)

    //Получить всех предприятия из hrm
    @GetMapping("/accounts")
    @SneakyThrows
    public void getAccounts() {
        String uri = "http://178.154.246.238:58082/api/accounts/";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", TOKEN);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<String> exchange = template.exchange(uri, HttpMethod.GET, entity, String.class);
        List<AccountDto> accounts = JacksonParser
                .getObjectMapper().readValue(exchange.getBody(), new TypeReference<List<AccountDto>>() {
                });
        accountsService.insertAllAccounts(accounts);
    }

    //Получить конкретное предприятие из hrm
    @GetMapping("/accounts/{id}")
    @SneakyThrows
    public void getAccountsById(@PathVariable("id") Integer userId) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", TOKEN);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<String> exchange =
                template.exchange(
                        uri.append(userId).append("/").toString(),
                        HttpMethod.GET,
                        entity,
                        String.class
                );
        AccountDto accounts = JacksonParser.getObjectMapper().readValue(exchange.getBody(), new TypeReference<AccountDto>() {
        });

        accountsService.insertAccount(accounts);
    }

    //Проапдейтить конкретное предприятие из hrm
    @PostMapping(
            value = "/accounts/update/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public void updateAccount(
            @PathVariable("id") Long userId,
            @RequestBody AccountDto account
    ) {
        accountsService.updateUserData(account);
    }

    @PostConstruct
    @SneakyThrows
    public void initAllAccountsBeforeStartServer() {
        startInitDataBase();
    }

    @Scheduled(cron = "0 0 0 * * *")
    @SneakyThrows
    public void updateUsersDataScheduled() {
        startInitDataBase();
    }
    private void startInitDataBase() throws JsonProcessingException {
        String uri = "http://178.154.246.238:58082/api/accounts/";
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", TOKEN);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<String> exchange = template.exchange(uri, HttpMethod.GET, entity, String.class);
        JacksonUtils.enhancedObjectMapper();
        List<AccountDto> accounts = JacksonParser.getObjectMapper().readValue(exchange.getBody(), new TypeReference<List<AccountDto>>() {
        });
        accountsService.insertAllAccounts(accounts);
    }
}
