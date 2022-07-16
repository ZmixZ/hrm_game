package com.example.hrm_game.controller;

import com.example.hrm_game.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestRestController {
    @Autowired
    private QuestService questService;

    @PostMapping("/quest/take")
    public void takeQuestFroUser(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "questId") Long questId
    ){
        questService.getQuestByUser(questId, userId);
    }

    @PostMapping("/quest/progress")
    public void progressQuest(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "questId") Long questId
    ){
//        questService.getQuestByUser(questId, userId);
    }
}
