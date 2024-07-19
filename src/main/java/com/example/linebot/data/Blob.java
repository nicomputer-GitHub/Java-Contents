package com.example.linebot.data;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import org.springframework.stereotype.Repository;

import com.linecorp.bot.client.LineBlobClient;
import com.linecorp.bot.client.MessageContentResponse;

import java.io.InputStream;

@Repository
public class Blob {
    private LineBlobClient blob;

    public Blob(LineBlobClient blob) {
        this.blob = blob;
    }

    // 画像データ取得
    public Resource getImageResource(MessageEvent<ImageMessageContent> event) throws Exception{

        // 送られたLINEのメッセージIDを取得する。
        String msgId = event.getMessage().getId();

        // BlobからメッセージIDと対応する画像の取得準備をする。
        MessageContentResponse contentResponse = blob.getMessageContent(msgId).get();

        try (InputStream is = contentResponse.getStream()) {
            LINEImageResource resource = new LINEImageResource(is.readAllBytes());
            return resource;
         }
    }
}
