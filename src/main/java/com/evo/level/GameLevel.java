package com.evo.level;

import com.evo.config.GameLevelNumber;
import com.evo.config.GameSetting;
import com.evo.entity.BufferedImageEntity;
import com.evo.entity.EntityManager;
import com.evo.entity.Vehicle;
import com.evo.modal.GamePanel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;

;
import java.awt.Component;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public abstract class GameLevel extends GamePanel{

    private Log log = LogFactory.getLog(GameLevel.class);
    private boolean gameActive = false;
    protected String worldMapFileName = "";
    private int totalNumberWorldPixelColX = 0;
    private int totalNumberWorldPixelColY = 0;
    private int pixelMapOccupiedTracker[][];
    private int totalMappedPixelCount = 0;
    private List<String> worldMapText = new ArrayList<>();

    private int cameraStartPosX = 0; //default
    private int cameraStartPosY = 0; //default
    private int cameraEndPosX = 20; //default
    private int cameraEndPosY = 20; //

    protected GameLevelNumber gameLevelNumber = null;
    protected String defaultBackgroundHex = "#99ccff";
    protected Thread gameThread;
    protected EntityManager entityManager;
    protected Vehicle vehicle;
    protected abstract void initGameThread();

    public abstract Component render();
    public abstract void init();
    public abstract GameLevelNumber getGameLevelNumber();
    public abstract void moveAllCharactersToStartPosition();

    public boolean isGameActive(){
        return gameActive;
    }

    protected void setGameActive(boolean gameActive){
        this.gameActive = gameActive;
    }

    protected void setPixelMapSize(int y, int x){

        totalNumberWorldPixelColY = y;
        totalNumberWorldPixelColX = x;

        pixelMapOccupiedTracker = new int[totalNumberWorldPixelColY][totalNumberWorldPixelColX];

        cameraEndPosX = gameSetting.getScreenWidth();
        cameraEndPosY = gameSetting.getScreenHeight();
    }

    protected void readAndGenerateWorldMap() {
        worldMapText = new ArrayList<>();

        BufferedReader fileReader = null;

        try{
            File file = new File(new ClassPathResource(worldMapFileName).getURI());

            if(!file.exists()){
                throw new Exception(String.format("World map file: %s, Cannot be found", worldMapFileName));
            }

            Path path = Paths.get(file.getAbsolutePath());

            fileReader = Files.newBufferedReader(path);
            String fileLine;

            while ((fileLine = fileReader.readLine()) != null) {

                if (fileLine.isEmpty()) {
                    throw new Exception(String.format("World map file: %s is empty", worldMapFileName));
                }

                worldMapText.add(fileLine);
            }

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

    protected void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }



    protected void paintGameContent(Graphics2D graphics2D){

        pixelMapOccupiedTracker = new int[totalNumberWorldPixelColY][totalNumberWorldPixelColX];

        try{
            for(int y=0; y < worldMapText.size(); y++){
                generateWorldMapFromRow(worldMapText.get(y), y, graphics2D);
            }

            vehicle.draw(graphics2D);
        }catch (Exception e){
            log.error(String.format("Error while painting game content: %s", e.getMessage()), e);
        }


    }

    private void generateWorldMapFromRow(String fileContentRow, int rowNumber, Graphics2D graphics2D) throws Exception{

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

        for(int xDrawPixelPos=0, colContentCounter=0; xDrawPixelPos < totalNumberWorldPixelColX; xDrawPixelPos++, colContentCounter++){
            String entityConfig[] = tileContent[colContentCounter].split("=");
            String xyPos[] = entityConfig[1].split("x");

            //FIXME: we do not want to replace an already added pixel here.
            // Check using the pixel tracker if at the start of the pixel point its not occupied

            if(isPixelDrawPointOccupied(rowNumber, xDrawPixelPos)){
                throw new Exception(String.format("Draw point %sx%s is already occupied", xDrawPixelPos, rowNumber));
            }

            cameraStartPosX = getCameraStartXPos();
            cameraEndPosX = getCameraEndXPos();

            int actualYDrawPos = gameSetting.getActualScreenYPos(rowNumber);
            int actualXDrawPos = gameSetting.getActualScreenXPos(xDrawPixelPos) - cameraStartPosX;

            if(!entityConfig[0].equals("NONE") ){ //with NONE we don't add the entity we simply skip them

                BufferedImageEntity entity = entityManager.getEntity(entityConfig[0]);
                entity.setLocation(actualXDrawPos, actualYDrawPos);
//                gamePanel.addDrawableEntity(entity);
                entity.draw(graphics2D);
            }


            int xNumbPixelToDraw = Integer.parseInt(xyPos[0]);
            int yNumberPixelToDraw = Integer.parseInt(xyPos[1]); // we do not need to get this since the rowNumber is the yDrawPost in this instance
            //We only need this for tracking purpose

            //loop through all the coordinates and mark them as occupied
            for(int y=0; y < yNumberPixelToDraw; y++){
                for(int x=0; x < xNumbPixelToDraw; x++){
                    markPixelDrawPointAsOccupied(y, x);
                }

            }

            xDrawPixelPos += xNumbPixelToDraw - 1; //increment the counter, subtract 1 since arrays start from 0 and end at totalSize - 1
           //now fill up each corresponding pixel occupied tracker

        }


    }

    private int getCameraEndXPos() {
        return vehicle.getCurrentXPos() + gameSetting.getScreenWidth();
    }

    private int getCameraStartXPos() {
        return vehicle.getCurrentXPos() + vehicle.getCurrentSpeed();
    }

    private void markPixelDrawPointAsOccupied(int y, int x){
        pixelMapOccupiedTracker[y][x] = 1; //in a 2d the first part is the row which is y axis
        totalMappedPixelCount++;
    }

    private boolean isPixelDrawPointOccupied(int x, int y){
        return pixelMapOccupiedTracker[x][y] == 1; // 1 means true;
    }
}
