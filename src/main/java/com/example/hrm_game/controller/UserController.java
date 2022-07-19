package com.example.hrm_game.controller;

import com.example.hrm_game.data.dto.AccountDto;
import com.example.hrm_game.data.dto.UserDto;
import com.example.hrm_game.data.entity.UserEntity;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.hrm_game.data.URLConst.API;
import static com.example.hrm_game.data.URLConst.HOST;

@RestController
public class UserController {
    @Autowired
    private UserService accountsService;
    //TODO: сделать проливку пользователей здесь
    ObjectMapper mapper = new ObjectMapper();
    StringBuilder uri = new StringBuilder().append(HOST).append(API);
    private final static String TOKEN = "Token 3ed364ddfe4bb9817ee7328b64b1fcb9acf09407";

    @GetMapping("/user/{id}")
    @SneakyThrows
    public ResponseEntity<UserEntity> getUser(@PathVariable("id") Long userId){
        return ResponseEntity.ok(accountsService.getUserFromId(userId));
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
//        String uri = "http://178.154.246.238:58082/api/employees/";
//        RestTemplate template = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.set("Authorization", TOKEN);
//
//        HttpEntity<String> entity = new HttpEntity<>("body", headers);
//
//        ResponseEntity<String> exchange = template.exchange(uri, HttpMethod.GET, entity, String.class);
//        JacksonUtils.enhancedObjectMapper();
//        List<UserDto> users = JacksonParser.getObjectMapper().readValue(exchange.getBody(), new TypeReference<List<UserDto>>() {
//        });
        List<UserDto> users = new ArrayList<>();
        accountsService.insertAllUser(users);
    }
}
