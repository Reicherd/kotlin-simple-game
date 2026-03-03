package arw.simple.game.tile

import arw.simple.game.commons.FormativeConstants.MAX_SCREEN_COL
import arw.simple.game.commons.FormativeConstants.MAX_SCREEN_ROW
import arw.simple.game.commons.FormativeConstants.TILE_SIZE
import arw.simple.game.model.Tile
import arw.simple.game.panel.GamePanel
import java.awt.Graphics2D
import javax.imageio.ImageIO

class TileManager {

    val gamePanel: GamePanel
    val tiles: MutableList<Tile> = mutableListOf()

    constructor(gamePanel: GamePanel) {
        this.gamePanel = gamePanel
        getTileImage()
    }

    fun getTileImage() {
        runCatching {
            tiles.add(Tile(image = ImageIO.read(javaClass.getResourceAsStream("/tiles/grass.png"))))
            tiles.add(Tile(image = ImageIO.read(javaClass.getResourceAsStream("/tiles/wall.png"))))
            tiles.add(Tile(image = ImageIO.read(javaClass.getResourceAsStream("/tiles/water.png"))))
        }.onFailure {
            it.printStackTrace()
        }
    }

    fun draw(graphics2D: Graphics2D) {
        var col = 0
        var row = 0
        var x = 0
        var y = 0

        while (col < MAX_SCREEN_COL && row < MAX_SCREEN_ROW) {
            graphics2D.drawImage(tiles[0].image, x, y, TILE_SIZE, TILE_SIZE, null)
            col++
            x += TILE_SIZE

            if (col == MAX_SCREEN_COL) {
                col = 0
                x = 0
                row++
                y += TILE_SIZE
            }

        }
    }
}