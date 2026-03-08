package arw.simple.game.collision

import arw.simple.game.commons.FormativeConstants.TILE_SIZE
import arw.simple.game.panel.GamePanel
import arw.simple.game.player.Entity
import arw.simple.game.player.PlayerDirection
import arw.simple.game.tile.TileDesign

class CollisionChecker(
    val gamePanel: GamePanel,
) {

    fun checkTile(entity: Entity) {
        val entityLeftWorldX = entity.worldX + entity.solidArea.x
        val entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width
        val entityTopWorldY = entity.worldY + entity.solidArea.y
        val entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height

        var entityLeftX = entityLeftWorldX / TILE_SIZE
        var entityRightX = entityRightWorldX / TILE_SIZE
        var entityTopY = entityTopWorldY / TILE_SIZE
        var entityBottomY = entityBottomWorldY / TILE_SIZE

        var firstTile: TileDesign
        var secondTile: TileDesign

        when (entity.direction) {
            PlayerDirection.UP -> {
                entityTopY = (entityTopWorldY - entity.speed) / TILE_SIZE

                firstTile = gamePanel.tileManager.mapTileNumber[entityTopY][entityLeftX]
                secondTile = gamePanel.tileManager.mapTileNumber[entityTopY][entityRightX]

                println(entityTopY)
                println(entityLeftX)
                verifyCollision(firstTile, secondTile, entity)
            }
            PlayerDirection.DOWN -> {
                entityBottomY = (entityBottomWorldY + entity.speed) / TILE_SIZE

                firstTile = gamePanel.tileManager.mapTileNumber[entityBottomY][entityLeftX]
                secondTile = gamePanel.tileManager.mapTileNumber[entityBottomY][entityRightX]

                verifyCollision(firstTile, secondTile, entity)
            }
            PlayerDirection.LEFT ->  {
                entityLeftX = (entityLeftWorldX - entity.speed) / TILE_SIZE

                firstTile = gamePanel.tileManager.mapTileNumber[entityTopY][entityLeftX]
                secondTile = gamePanel.tileManager.mapTileNumber[entityBottomY][entityLeftX]

                verifyCollision(firstTile, secondTile, entity)
            }
            PlayerDirection.RIGHT ->  {
                entityRightX = (entityRightWorldX + entity.speed) / TILE_SIZE

                firstTile = gamePanel.tileManager.mapTileNumber[entityTopY][entityRightX]
                secondTile = gamePanel.tileManager.mapTileNumber[entityBottomY][entityRightX]

                verifyCollision(firstTile, secondTile, entity)
            }
            else -> {}
        }
    }

    private fun verifyCollision(firstTile: TileDesign, secondTile: TileDesign, entity: Entity) {
        if (gamePanel.tileManager.tileDesigns[firstTile]?.collision == true
            || gamePanel.tileManager.tileDesigns[secondTile]?.collision == true
        ) {
            entity.collisionEnabled = true
        }
    }
}