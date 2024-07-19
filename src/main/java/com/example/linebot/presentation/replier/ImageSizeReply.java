package com.example.linebot.presentation.replier;

import com.example.linebot.service.JankenResult;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class ImageSizeReply implements Replier {
    public static final String MESSAGE_FORMAT = "画像サイズ : %d";
    private final JankenResult jankenResult;

    public ImageSizeReply(JankenResult jankenResult) {
        this.jankenResult = jankenResult;
    }

    @Override
    public Message reply() {
        String message = String.format(MESSAGE_FORMAT, jankenResult.imageSize());
        return new TextMessage(message);
    }
}
