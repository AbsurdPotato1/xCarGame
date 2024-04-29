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

        solidArea = new Rectangle(0, 0, 898 / 4, 436/4); //436 /4

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = 50;
        worldY = 50;
        speedHor = 4 * 60.0 / (double)gp.FPS;
        speedVert = 4 * 60.0 / (double)gp.FPS;
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
    public void jump(){
        worldY -= curUpSpeed;
        curUpSpeed -= gravity;
    }
    public void update(){
        System.out.println(worldY);
//        System.out.println(jumping);
        if(curUpSpeed > 0){
            direction[0] = true;
        }else{
            direction[0] = false;
        }
        if(keyH.rightPressed){
            direction[1] = true;
        }else{
            direction[1] = false;
        }
        if(curUpSpeed < 0){
            direction[2] = true;
        }else{
            direction[2] = false;
        }
        if(keyH.leftPressed){
            direction[3] = true;
        }else{
            direction[3] = false;
        }

//        if(keyH.jumpPressed){
//            direction = "up";
//        }
//        if(keyH.leftPressed){
//            direction = "left";
//        }
//        if(keyH.rightPressed){
//            direction = "right";
//        }
//        if(keyH.downPressed){
//            direction = "down";
//        }
        //CHECK TILE COLLISION
//        System.out.println(downCollisionOn);
        upCollisionOn = false;
        rightCollisionOn = false;
        downCollisionOn = false;
        leftCollisionOn = false;
        gp.cChecker.checkTile(this);
        // TODO: player locks to one pixel higher than they should on downCollision if left or right movement is happening, can't jump as a result.
        // IF COLLISION IS FALSE, PLAYER CAN MOVE
//        System.out.println(downCollisionOn);
//        System.out.println(upCollisionOn);
        if (downCollisionOn) {
            // Snap player to the nearest tile below
            int playerBottomY = worldY + solidArea.height; // Calculate the bottom Y-coordinate of the player
            int nearestTileBelowY = ((playerBottomY + gp.tileSize - 1) / gp.tileSize) * gp.tileSize; // Calculate nearest tile below

            // Calculate the distance to the nearest tile above
            int nearestTileAboveY = nearestTileBelowY - gp.tileSize;
            int distToTileAbove = worldY + solidArea.height - nearestTileAboveY;

            // Calculate the distance to the nearest tile below
            int distToTileBelow = nearestTileBelowY - (worldY + solidArea.height);

            // Snap to the nearest tile (above or below)
            if (distToTileAbove < distToTileBelow) {
                worldY = (nearestTileAboveY + 6) / gp.tileSize * gp.tileSize - solidArea.height;
            } else {
                worldY = (nearestTileBelowY + 6) / gp.tileSize * gp.tileSize - solidArea.height;
            }

            jumping = false;
            curUpSpeed = 0;
        } else {
            jumping = true;
        }
//        if(!downCollisionOn)jumping = true;
        if(upCollisionOn && jumping)curUpSpeed = 0;
        if(jumping)jump();
        if (keyH.jumpPressed && !jumping) {
            jumping = true;
            curUpSpeed = 10 * 60 / (double)gp.FPS;
        }
        System.out.println(downCollisionOn);
        if(!rightCollisionOn) {
            if (keyH.rightPressed) {
                worldX += speedHor;
                gp.cChecker.checkTile(this);
            }
        }
//        if(!downCollisionOn) {
//            if (keyH.downPressed && !jumping) {
//                worldY += speedVert;
//                gp.cChecker.checkTile(this);
//            }
//        }
//        System.out.println(leftCollisionOn);
        if(!leftCollisionOn) {
            if (keyH.leftPressed) {
                worldX -= speedHor;
                gp.cChecker.checkTile(this);
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        image = car1;
        g2.drawImage(image, (int)worldX, (int)worldY, 898 / 4, 436/4, null);


    }


}
