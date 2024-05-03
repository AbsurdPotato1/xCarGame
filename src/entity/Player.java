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

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle(0, 0, 898 / 4, 436/4); //436 /4

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = 50;
        worldY = 50;
        speedHor = 4 * 60.0 / (double)gp.FPS;
    }

    public void getPlayerImage(){
        try {
            car1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/StartingCarTransparentCropped.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void snapPlayerLoc() {
        if (upCollisionOn) {
            // Snap player to the nearest tile below
            int playerTopY = worldY; // Calculate the bottom Y-coordinate of the player
            int nearestTileAboveY = ((playerTopY) / gp.tileSize) * gp.tileSize; // Calculate nearest tile above

            // Calculate the distance to the nearest tile above
            int nearestTileBelowY = nearestTileAboveY + gp.tileSize;
            int distToTileAbove = worldY - nearestTileAboveY; // should always be positive

            // Calculate the distance to the nearest tile below
            int distToTileBelow = nearestTileBelowY - worldY;

            // Snap to the nearest tile (above or below)
            if (distToTileAbove < distToTileBelow) {
                worldY = (nearestTileAboveY + 6) / gp.tileSize * gp.tileSize;
            } else {
                worldY = (nearestTileBelowY + 6) / gp.tileSize * gp.tileSize;
            }

            jumping = true;
            curUpSpeed = 0;
            gp.cChecker.checkTile(this);
        }
        if (rightCollisionOn) {
//            while(rightCollisionOn){
//                gp.cChecker.checkTile(this);
//                worldX--;
//            }
            // Snap player to the nearest tile below
            int playerRightX = worldX + solidArea.x + solidArea.width; // Calculate the right X-coordinate of the player
            int nearestTileRightX = ((playerRightX + gp.tileSize - 1) / gp.tileSize) * gp.tileSize; // Calculate nearest tile right

            // Calculate the distance to the nearest tile above
            int nearestTileLeftX = nearestTileRightX - gp.tileSize;
            int distToTileLeft = worldX + solidArea.x + solidArea.width - nearestTileLeftX; // should always be positive

            // Calculate the distance to the nearest tile below
            int distToTileRight = nearestTileRightX - (worldX + solidArea.width);

            // Snap to the nearest tile (above or below)
            if (distToTileLeft < distToTileRight) {
                worldX = (nearestTileLeftX + 6) / gp.tileSize * gp.tileSize - solidArea.width;
            } else {
                worldX = (nearestTileRightX + 6) / gp.tileSize * gp.tileSize - solidArea.width;
            }
            gp.cChecker.checkTile(this);
        }
        if (downCollisionOn) {
            // Snap player to the nearest tile below
            int playerBottomY = worldY + solidArea.y + solidArea.height; // Calculate the bottom Y-coordinate of the player
            int nearestTileBelowY = ((playerBottomY + gp.tileSize - 1) / gp.tileSize) * gp.tileSize; // Calculate nearest tile below

            // Calculate the distance to the nearest tile above
            int nearestTileAboveY = nearestTileBelowY - gp.tileSize;
            int distToTileAbove = worldY + solidArea.y + solidArea.height - nearestTileAboveY; // should always be positive

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
        gp.cChecker.checkTile(this);
    }
    public void jump(){
        worldY -= curUpSpeed;
        curUpSpeed -= gravity;
    }
    public void update(){
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

        upCollisionOn = false;
        rightCollisionOn = false;
        downCollisionOn = false;
        leftCollisionOn = false;
        gp.cChecker.checkTile(this);
        // TODO: player locks to one pixel higher than they should on downCollision if left or right movement is happening, can't jump as a result.
        // IF COLLISION IS FALSE, PLAYER CAN MOVE

        snapPlayerLoc();
        System.out.println(upCollisionOn);

//        if(!downCollisionOn)jumping = true;
        if(upCollisionOn && jumping)curUpSpeed = 0;
        if(jumping)jump();
        if (keyH.jumpPressed && !jumping) {
            jumping = true;
            curUpSpeed = 10 * 60 / (double)gp.FPS;
        }
        if(!rightCollisionOn) {
            if (keyH.rightPressed) {
                worldX += speedHor;
            }
        }
        if(!leftCollisionOn) {
            if (keyH.leftPressed) { // BUG RELATED: when keyH.leftPressed, left Collision is enabled for some reason
                worldX -= speedHor;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        image = car1;
        g2.drawImage(image, (int)worldX, (int)worldY, 898 / 4, 436/4, null);


    }


}
