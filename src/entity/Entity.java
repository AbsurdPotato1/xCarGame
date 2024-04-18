package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speedVert, speedHor;
    public String direction = "up";
    public final int gravity = 10;
    public BufferedImage car1;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
//    public boolean rightCollisionOn = false;
//    public boolean topCollisionOn = false;
//    public boolean bottomCollisionOn = false;
}
