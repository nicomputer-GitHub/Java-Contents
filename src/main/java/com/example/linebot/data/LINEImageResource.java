package com.example.linebot.data;

import org.springframework.core.io.ByteArrayResource;

public class LINEImageResource extends ByteArrayResource {
    public LINEImageResource(byte[] byteArray) {
        super(byteArray);
    }

    @Override
    public String getFilename() {
        return "image.jpg";
    }
}
