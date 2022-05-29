package com.evo.level;

import com.evo.config.AppConfig;
import com.evo.config.GameLevelNumber;
import com.evo.config.GameSetting;
import com.evo.entity.BufferedImageEntity;
import com.evo.entity.EntityManager;
import com.evo.modal.GamePanel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;

import java.awt.*;
;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public abstract class GameLevel{

    private Log log = LogFactory.getLog(GameLevel.class);
    private boolean gameActive = false;
    private String worldMapFileName = "";
    private int totalNumberWorldPixelColX = 0;
    private int totalNumberWorldPixelColY = 0;
    private int pixelMapOccupiedTracker[][];
    private int totalMappedPixelCount = 0;

    protected GameLevelNumber gameLevelNumber = null;
    protected String defaultBackgroundHex = "#99ccff";
    protected GamePanel gamePanel;
    protected GameSetting gameSetting;
    protected Thread gameThread;
    protected EntityManager entityManager;
    protected abstract void initGameThread();

    public abstract Component render();
    public abstract void addDefaultCharactersToScene();
    public abstract GameLevelNumber getGameLevelNumber();
    public abstract void moveAllCharactersToStartPosition();

    public boolean isGameActive(){
        return gameActive;
    }

    protected void setGameActive(boolean gameActive){
        this.gameActive = gameActive;
    }

    protected void setWorldMapFileName(String fileName){
        worldMapFileName = fileName;
    }

    protected void setPixelMapSize(int x, int y){
        pixelMapOccupiedTracker = new int[x][y];

        totalNumberWorldPixelColY = y;
        totalNumberWorldPixelColX = x;
    }

    protected void readAndGenerateWorldMap(EntityManager entityManager, GameSetting gameSetting){
        this.entityManager = entityManager;
        this.gameSetting = gameSetting;

        if(worldMapFileName != null && !worldMapFileName.isEmpty()){

            BufferedReader fileReader = null;

            try{
                File file = new File(new ClassPathResource(worldMapFileName).getURI());

                if(!file.exists()){
                    throw new Exception(String.format("World map file: %s, Cannot be found", worldMapFileName));
                }

                Path path = Paths.get(file.getAbsolutePath());

                fileReader = Files.newBufferedReader(path);
                String fileLine;
                int rowNumber = 0;
                while ((fileLine = fileReader.readLine()) != null) {

                    if (fileLine.isEmpty()) {
                        throw new Exception(String.format("World map file: %s is empty", worldMapFileName));
                    }

                    generateWorldMapFromRow(fileLine, rowNumber++);

                }

                fileReader.close();

            }catch (Exception e){

                log.error(String.format("Error while building default map : %s", e), e);
                System.exit(1); //Should the game exit here, yes because no map no poor game visuals
            }finally {
                try{
                    if(fileReader != null){
                        fileReader.close();
                    }
                }catch (Exception e){
                    log.error(e);
                }
            }

        }
    }

    private void generateWorldMapFromRow(String fileContentRow, int rowNumber) throws Exception{

        totalMappedPixelCount = 0;

        String tileContent[] = fileContentRow.trim().split(" ");

        if(tileContent.length < 1){
            throw new Exception(String.format("Now content for given row"));
        }

        int pixelColCounter = 0; // Counter to make sure we have each expected column filled

        //expects a row that looks like:  NONE=1X1 NONE=1X1 NONE=1X1...
        //or even a single entry that covers all the rows like: NONE=20X1
        //first part is the entity type 'NONE' means just let default background colour.
        // while =20x1 is the col x row it covers

        for(int xDrawPos=0, colContentCounter=0; xDrawPos < totalNumberWorldPixelColX; xDrawPos++, colContentCounter++){
            String entityConfig[] = tileContent[colContentCounter].split("=");
            String xyPos[] = entityConfig[1].split("x");

            //FIXME: we do not want to replace an already added pixel here.
            // Check using the pixel tracker if at the start of the pixel point its not occupied

            int xNumbPixelToDraw = Integer.parseInt(xyPos[0]);
            int yNumberPixelToDraw = Integer.parseInt(xyPos[1]); // we do not need to get this since the rowNumber is the yDrawPost in this instance
            //We only need this for tracking purpose

            if(isPixelDrawPointOccupied(rowNumber, xDrawPos)){
                throw new Exception(String.format("Draw point %sx%s is already occupied", xDrawPos, rowNumber));
            }

            if(!entityConfig[0].equals("NONE")){ //with NONE we don't add the entity we simply skip them
                BufferedImageEntity entity = entityManager.getEntity(entityConfig[0]);
                entity.setDefaultLocation(gameSetting.getActualScreenXPos(xDrawPos), gameSetting.getActualScreenYPos(rowNumber));
                gamePanel.addDrawableEntity(entity);
            }

            //loop through all the coordinates and mark them as occupied
            for(int y=0; y < yNumberPixelToDraw; y++){
                for(int x=0; x < xNumbPixelToDraw; x++){
                    markPixelDrawPointAsOccupied(y, x);
                }

            }

            xDrawPos += xNumbPixelToDraw - 1; //increment the counter, subtract 1 since arrays start from 0 and end at totalSize - 1
           //now fill up each corresponding pixel occupied tracker

        }


    }

    private void markPixelDrawPointAsOccupied(int y, int x){
        pixelMapOccupiedTracker[y][x] = 1; //in a 2d the first part is the row which is y axis
        totalMappedPixelCount++;
    }

    private boolean isPixelDrawPointOccupied(int x, int y){
        return pixelMapOccupiedTracker[x][y] == 1; // 1 means true;
    }

}
