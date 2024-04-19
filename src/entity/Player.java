package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

//    public final int screenX;
//    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

//        screenX = gp.screenWidth / 2;
//        screenY = gp.screenHeight / 2;

        solidArea = new Rectangle(8, 16, 898/4, 436/4);

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = 50;
        worldY = 50;
        speedHor = 4;
        speedVert = 4;
    }

    public void getPlayerImage(){
//        System.out.println("Image loading started");
        try {
            car1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/StartingCarTransparentCropped.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("Image loading ended");
    }

    public void update(){
        if(keyH.jumpPressed){
            direction = "up";
        }
        if(keyH.leftPressed){
            direction = "left";
        }
        if(keyH.rightPressed){
            direction = "right";
        }
        if(keyH.downPressed){
            direction = "down";
        }
        //CHECK TILE COLLISION
        collisionOn = false;
        gp.cChecker.checkTile(this);


        // IF COLLISION IS FALSE, PLAYER CAN MOVE
        if(!collisionOn){
            if(keyH.leftPressed)
                worldX -= speedHor;
            if(keyH.rightPressed)
                worldX += speedHor;
            if(keyH.jumpPressed)
                worldY -= speedVert;
            if(keyH.downPressed)
                worldY += speedVert;
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        image = car1;
        g2.drawImage(image, worldX, worldY, 898 / 4, 436 / 4, null);


    }


}
