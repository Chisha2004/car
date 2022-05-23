package com.evo.config;

import com.evo.engine.DefaultGearBox;
import com.evo.engine.DefaultVehicleEngine;
import com.evo.engine.GearBox;
import com.evo.engine.VehicleEngine;
import com.evo.level.GameLevel;
import com.evo.level.GameLevelOne;
import com.evo.modal.Vehicle;
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
        return new Vehicle(1);
    }

}
