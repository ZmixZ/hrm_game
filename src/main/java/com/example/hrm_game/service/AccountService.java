package com.example.hrm_game.service;

import com.example.hrm_game.data.dto.AccountDto;
import com.example.hrm_game.data.dto.UserDto;
import com.example.hrm_game.data.entity.AccountEntity;
import com.example.hrm_game.data.entity.LevelEntity;
import com.example.hrm_game.data.entity.UserEntity;
import com.example.hrm_game.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public void insertAllAccounts(List<AccountDto> accounts){
        accounts.forEach(
                account -> {
                    try {
                        //TODO: Задать вопрос: меняются ли в HRM данные
                        // и стоит ли их при проливке апдейтить или только добавлять новых, как сейчас?
                        AccountEntity accountEntityById = accountRepository.findAccountEntityById(account.getId());
                        if (accountEntityById == null) {
                            //Создаем юзера, если он отсутствует в БД приложения
                            AccountEntity accountEntity = new AccountEntity();
                            accountEntity.setId(account.getId());
                            accountEntity.setName(account.getName());
                            accountEntity.setActiveAdmin(account.getActiveAdmin());
                            accountRepository.save(accountEntity);
                        } else {
                            //Обновляем имя и админа, вдруг поменялись
                            accountEntityById.setName(account.getName());
                            accountEntityById.setActiveAdmin(account.getActiveAdmin());
                        }
                    } catch (NullPointerException ex) {
                        log.error("Пустой айди у пользователя " + account.getId());
                    }
                }
        );
    }

    public void insertAccount(AccountDto account) {
        try {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setId(account.getId());
            accountEntity.setName(account.getName());
            accountEntity.setActiveAdmin(account.getActiveAdmin());
            accountRepository.save(accountEntity);
        } catch (NullPointerException ex) {
            log.error("Пустой айди у пользователя " + account.getId());
        }
    }

    //Получить предприятие из внутреннего сервиса по айди
    public AccountEntity getAccountFromId(Long id) {
        return accountRepository.findAccountEntityById(id);
    }
    public void updateUserData(AccountDto account) {
        //TODO: мб как-то убрать или подумать что с ним делать
        AccountEntity accountEntity = accountRepository.findAccountEntityById(account.getId());
        accountEntity.setName(account.getName());
        accountEntity.setActiveAdmin(account.getActiveAdmin());
        accountRepository.save(accountEntity);
    }

    //Проапдейтить предприятие из внутреннего сервиса по айди
    public void updateUserData(AccountEntity accountEntity){
        accountRepository.save(accountEntity);
    }
}
