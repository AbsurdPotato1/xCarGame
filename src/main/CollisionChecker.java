package main;


import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;
        switch(entity.direction){
            case "up":
                entityTopRow  = (entityTopWorldY - entity.speedVert)/gp.tileSize;
                for(int i = entityLeftCol; i <= entityRightCol; i++){
                    tileNum1 = gp.tileM.mapTileNum[entityTopRow][i];
                    if(gp.tileM.tile[tileNum1].collision) {
                        entity.collisionOn = true;
                        break;
                    }
                }
//                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
//                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
//                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
//                    entity.collisionOn = true;
                break;
            case "down":
                entityBottomRow  = (entityBottomWorldY + entity.speedVert)/gp.tileSize;
                for(int i = entityLeftCol; i <= entityRightCol; i++){
                    tileNum1 = gp.tileM.mapTileNum[entityBottomRow][i];
                    if(gp.tileM.tile[tileNum1].collision) {
                        entity.collisionOn = true;
                        break;
                    }
                }
//                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
//                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
//                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
//                    entity.collisionOn = true;
                break;
            case "left":
                entityLeftCol  = (entityLeftWorldX - entity.speedHor)/gp.tileSize;
                for(int i = entityTopRow; i <= entityBottomRow; i++) {
                    tileNum1 = gp.tileM.mapTileNum[i][entityLeftCol];
                    if(gp.tileM.tile[tileNum1].collision) {
                        entity.collisionOn = true;
                        break;
                    }
                }
//                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
//                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
//                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
//                    entity.collisionOn = true;
                break;
            case "right":
                entityRightCol  = (entityRightWorldX + entity.speedHor)/gp.tileSize;
                for(int i = entityTopRow; i <= entityBottomRow; i++) {
                    tileNum1 = gp.tileM.mapTileNum[i][entityRightCol];
                    if(gp.tileM.tile[tileNum1].collision) {
                        entity.collisionOn = true;
                        break;
                    }
                }
//                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
//                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
//                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
//                    entity.collisionOn = true;
                break;
        }
    }
}
