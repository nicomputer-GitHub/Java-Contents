package com.example.linebot.presentation.replier;

import com.example.linebot.service.JankenResult;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class JankenReply implements Replier {
    public static final String MESSAGE_FORMAT = "あなた : %s, 相手 : %s\n結果 : %s";
    private final JankenResult jankenResult;

    public JankenReply(JankenResult jankenResult) {
        this.jankenResult = jankenResult;
    }

    @Override
    public Message reply() {
        String jibun = convertToHand(jankenResult.response().jibun());
        String aite = convertToHand(jankenResult.response().aite());
        String kekka = convertToResult(jankenResult.response().kekka());

        String message = String.format(MESSAGE_FORMAT, jibun, aite, kekka);
        return new TextMessage(message);
    }

    private String convertToHand(int hand) {
        return switch (hand) {
            case 0 -> "グー";
            case 1 -> "チョキ";
            case 2 -> "パー";
            default -> "不明";
        };
    }

    private String convertToResult(int result) {
        return switch (result) {
            case 2 -> "勝ち";
            case 1 -> "負け";
            case 0 -> "引き分け";
            default -> "不明";
        };
    }
}
