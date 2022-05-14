package com.evo.config;

import com.evo.level.GameLevel;
import com.evo.level.GameLevelOne;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//    @Bean
//    public AppSetting appSetting(){
//        return new AppSetting();
//    }
    @Bean
    public GameLevelManager gameLevelManager(){
        return new GameLevelManager();
    }


    @Bean
    public GameLevel gameLevelOne(){
        return new GameLevelOne();
    }

}
