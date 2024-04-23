package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public double worldX, worldY;
    public double speedVert, speedHor;
//    public String direction = "up";
    public boolean[] direction = new boolean[4]; // goes clockwise from the top: 0 - up, 1 - right, 2 - down, 3 - left
    public final int gravity = 10;
    public BufferedImage car1;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean upCollisionOn = false;
    public boolean rightCollisionOn = false;
    public boolean downCollisionOn = false;
    public boolean leftCollisionOn = false;

}
