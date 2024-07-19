package com.example.linebot.service;

import com.example.linebot.data.JankenLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SenrekiService {
    private JankenLog jankenLog;

    public SenrekiService(JankenLog jankenLog) {
        this.jankenLog = jankenLog;
    }

    public Senreki calcSenreki() {
        List<JankenResponse> jankenResponses = jankenLog.selectAll();
        int gameCount = jankenResponses.size();
        int jibunWinCount = (int) jankenResponses.stream().filter(response -> response.kekka() == 2).count();
        float jibunWinRate = gameCount == 0 ? 0 : (float) jibunWinCount / gameCount;
        return new Senreki(gameCount, jibunWinCount, jibunWinRate);

    }
}
