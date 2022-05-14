package com.evo.config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

@Service
public class AppSetting {

    @Value("${app.width}")
    private int appWidth;
    @Value("${app.height}")
    private int appHeight;
    @Value("${app.ground.height}")
    private int appGroundHeight;

    public int getAppWidth() {
        return appWidth;
    }

    public int getAppHeight() {
        return appHeight;
    }

    public int getAppGroundHeight() {
        return appGroundHeight;
    }
}
