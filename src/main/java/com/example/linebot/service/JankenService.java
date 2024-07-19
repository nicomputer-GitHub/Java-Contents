package com.example.linebot.service;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.example.linebot.data.Blob;
import com.example.linebot.data.JankenAPI;
import com.example.linebot.data.JankenLog;

@Service
public class JankenService {
    private Blob blob;
    private JankenLog jankenLog;
    private JankenAPI jankenAPI;
  
    public JankenService(Blob blob, JankenAPI jankenAPI, JankenLog jankenLog){
        this.blob = blob;
        this.jankenAPI = jankenAPI;
        this.jankenLog = jankenLog;
    }

    public JankenResult doJanken(MessageEvent<ImageMessageContent> event)
            throws Exception{
        Resource imageResource = blob.getImageResource(event);

        JankenResponse jankenResponse = jankenAPI.playGame(imageResource);

        jankenLog.insert(jankenResponse);

        JankenResult jankenResult = new JankenResult(imageResource.contentLength(), jankenResponse);
        return jankenResult;
    }
}
