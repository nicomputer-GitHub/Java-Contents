package com.example.linebot.presentation.replier;

import com.example.linebot.service.Senreki;
import com.example.linebot.service.SenrekiService;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class SenrekiReply implements Replier {
    private final SenrekiService senrekiService;

    public SenrekiReply(SenrekiService senrekiService) {
        this.senrekiService = senrekiService;
    }

    @Override
    public Message reply() {
        Senreki senreki = senrekiService.calcSenreki();
        String responseMessage = String.format("あなたは %d 戦中 %d 勝(勝率 %d パーセント)です", senreki.gameCount(), senreki.jibunWinCount(), senreki.jibunWinRate());
        return new TextMessage(responseMessage);
    }
}
