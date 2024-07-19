package com.example.linebot.presentation;

import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.linebot.presentation.replier.Follow;
import com.example.linebot.presentation.replier.Parrot;
import com.example.linebot.presentation.replier.ImageSizeReply;
import com.example.linebot.presentation.replier.JankenReply;
import com.example.linebot.presentation.replier.SenrekiReply;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.message.ImageMessageContent;

import com.example.linebot.service.JankenService;
import com.example.linebot.service.SenrekiService;
import com.example.linebot.service.JankenResult;

import java.util.List;

@LineMessageHandler
public class Callback {
    private static final Logger log = LoggerFactory.getLogger(Callback.class);
    private JankenService jankenService;
    private SenrekiService senrekiService;

    public Callback(JankenService jankenService, SenrekiService senrekiService){
        this.jankenService = jankenService;
        this.senrekiService = senrekiService;
    }

    @EventMapping
    public Message handleFollow(FollowEvent event) {
        Follow follow = new Follow(event);
        return follow.reply();
    }

    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event) {
        String userMessage = event.getMessage().getText();
        if ("戦歴".equals(userMessage)) {
            SenrekiReply senrekiReply = new SenrekiReply(senrekiService);
            return senrekiReply.reply();
        } else {
            Parrot parrot = new Parrot(event);
            return parrot.reply();
        }
    }

    @EventMapping
    public List<Message> handleJanken(MessageEvent<ImageMessageContent> event)
            throws Exception{
        JankenResult jankenResult = jankenService.doJanken(event);
        return List.of(new ImageSizeReply(jankenResult).reply(), new JankenReply(jankenResult).reply());
    }
}
