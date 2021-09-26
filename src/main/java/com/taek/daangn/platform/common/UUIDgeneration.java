package com.taek.daangn.platform.common;

import java.util.UUID;

public class UUIDgeneration {

    public String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid.substring(0,9);
    }
}
