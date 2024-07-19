package com.example.linebot.presentation.replier;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class Parrot implements Replier {
    private MessageEvent<TextMessageContent> event;

    public Parrot(MessageEvent<TextMessageContent> event) {
        this.event = event;
    }

    @Override
    public Message reply() {
        TextMessageContent tmc = this.event.getMessage();
        String text = tmc.getText();
        return new TextMessage(text);
    }
}
