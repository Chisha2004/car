package com.evo.config;

import com.evo.engine.DefaultGearBox;
import com.evo.engine.DefaultVehicleEngine;
import com.evo.engine.GearBox;
import com.evo.engine.VehicleEngine;
import com.evo.entity.SimpleCloud;
import com.evo.level.GameLevel;
import com.evo.level.GameLevelOne;
import com.evo.entity.SimpleFlatGround;
import com.evo.entity.Vehicle;
import org.springframework.beans.factory.annotation.Lookup;
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

    @Bean
    public VehicleEngine vehicleEngine(){
        return new DefaultVehicleEngine();
    }

    @Bean
    public GearBox gearBox(){
        return new DefaultGearBox();
    }

    @Bean
    public Vehicle getVehicle(){
        return new Vehicle();
    }

    @Bean
    public SimpleFlatGround getSimpleFlatGround(){
        return new SimpleFlatGround();
    }

    @Bean
    public SimpleCloud getSimpleCloud(){
        return new SimpleCloud();
    }

}
