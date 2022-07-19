package com.example.hrm_game.controller;

import com.example.hrm_game.data.dto.QuestDto;
import com.example.hrm_game.data.entity.LevelEntity;
import com.example.hrm_game.data.entity.UserEntity;
import com.example.hrm_game.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;

@RestController
public class QuestRestController {
    @Autowired
    private QuestService questService;

    @PostMapping("/quest/take")
    public void takeQuestForUser(
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "quest_id") Long questId
    ) {
        //TODO: Сделать еще в энтити награду и оперировать с объектом, продумать как,
        // сохранять в бд и через интстейнсоф находить, что за тип объекта
        // или использовать ДТО с наградой типа обджект при получении награды с квеста и там инстейнсоф + добавление типа награды Юзеру
        questService.addQuestForUser(questId, userId);
    }

    @GetMapping(
            name = "/quest/progress/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public RedirectView progressQuest(
            @PathVariable(name = "id") Long userId,
            @RequestParam(name = "questId") Long questId,
            @RequestBody QuestDto questDto,
            HttpServletResponse response
    ) {
        boolean isProgressCompleted = questService.getQuestProgress(questId, userId, questDto);
        if (isProgressCompleted) {
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/quest/completed/" + userId);
        }
        //TODO: Сделать редирект на ту же страницу где и был
        return null;
    }

    @PostMapping(
            name = "/quest/completed/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public void completedQuest(
            @PathVariable(name = "id") Long userId,
            @RequestParam(name = "questId") Long questId,
            @RequestBody QuestDto questDto
    ) {
        questService.addAwards(questId, userId, questDto);
    }
}
