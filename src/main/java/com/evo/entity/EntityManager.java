package com.evo.entity;


import com.evo.config.AppConfig;
import com.evo.config.GameSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EntityManager {

    @Resource
    private AppConfig appConfig;
    @Resource
    private GameSetting gameSetting;

    @Autowired
    List<BufferedImageEntity> mapEntities;

    public BufferedImageEntity getEntity(String uniqueIdentifier){
        BufferedImageEntity entity = null;

        if(SimpleCloud.UNIQUE_MAP_IDENTIFIER_KEY.equals(uniqueIdentifier)){
            return new SimpleCloud(gameSetting);
        }else if(SimpleFlatGround.UNIQUE_MAP_IDENTIFIER_KEY.equals(uniqueIdentifier)){
            return new SimpleFlatGround(gameSetting);
        }else if(Vehicle.UNIQUE_MAP_IDENTIFIER_KEY.equals(uniqueIdentifier)){
            return appConfig.getVehicle();
        }

//        for(BufferedImageEntity bufferedImage : mapEntities){
//            if(bufferedImage.getUniqueMapIdentifier().equals(uniqueIdentifier)){
//                entity = bufferedImage;
//
//                break;
//            }
//        }

        return entity;
    }
}
