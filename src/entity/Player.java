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

        solidArea = new Rectangle(0, 0, 898 / 4, 436 / 4);

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
        if(jumping){
//            if(curUpSpeed <= 0)direction[2] = true;
//                curUpSpeed = 10 * 60.0 / (double) gp.FPS;
            worldY -= curUpSpeed;
            curUpSpeed -= gravity;
        }
    }
    public void update(){
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
        upCollisionOn = false;
        rightCollisionOn = false;
        downCollisionOn = false;
        leftCollisionOn = false;
        gp.cChecker.checkTile(this);

        // IF COLLISION IS FALSE, PLAYER CAN MOVE
//        System.out.println(downCollisionOn);
//        System.out.println(upCollisionOn);
        if(downCollisionOn)jumping = false;
        if(!downCollisionOn)jumping = true;
        if(upCollisionOn && jumping)curUpSpeed = 0;
        if(!upCollisionOn) {
            if(jumping)jump();
            if (keyH.jumpPressed && !jumping) {
                jumping = true;
                curUpSpeed = 10 * 60 / (double)gp.FPS;
//                worldY -= speedVert;
//                gp.cChecker.checkTile(this);
            }
        }

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
        g2.drawImage(image, (int)worldX, (int)worldY, 898 / 4, 436 / 4, null);


    }


}
