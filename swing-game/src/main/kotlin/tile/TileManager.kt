package arw.simple.game.tile

import arw.simple.game.commons.FormativeConstants.TILE_SIZE
import arw.simple.game.model.Tile
import arw.simple.game.panel.GamePanel
import java.awt.Graphics2D
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.imageio.ImageIO

class TileManager {

    val tileDesigns: MutableMap<TileDesign, Tile> = mutableMapOf()
    val gamePanel: GamePanel

    val mapTileNumber: MutableList<List<TileDesign>> = mutableListOf()
    constructor(gamePanel: GamePanel) {
        this.gamePanel = gamePanel
        getTileImage()
        loadMap("/maps/world01")
    }

    fun getTileImage() {
        runCatching {
            tileDesigns[TileDesign.GRASS] =
                Tile(image = ImageIO.read(javaClass.getResourceAsStream("/tiles/grass.png")))
            tileDesigns[TileDesign.WALL] =
                Tile(image = ImageIO.read(javaClass.getResourceAsStream("/tiles/wall.png")), collision = true)
            tileDesigns[TileDesign.WATER] =
                Tile(image = ImageIO.read(javaClass.getResourceAsStream("/tiles/water.png")), collision = true)
            tileDesigns[TileDesign.SAND] =
                Tile(image = ImageIO.read(javaClass.getResourceAsStream("/tiles/sand.png")))
            tileDesigns[TileDesign.EARTH] =
                Tile(image = ImageIO.read(javaClass.getResourceAsStream("/tiles/earth.png")))
            tileDesigns[TileDesign.TREE] =
                Tile(image = ImageIO.read(javaClass.getResourceAsStream("/tiles/tree.png")), collision = true)
        }.onFailure {
            it.printStackTrace()
        }
    }

    fun loadMap(mapFile: String) {
        runCatching {

            val inputStream = javaClass.getResourceAsStream(mapFile)
                        val bufferedReader = BufferedReader(InputStreamReader(inputStream!!))

            bufferedReader.readLines().forEach { line ->
                mapTileNumber.add(line.split(" ").map { TileDesign.fromValue(it.toInt()) })
            }

            bufferedReader.close()
        }.onFailure {
            it.printStackTrace()
        }
    }

    fun draw(graphics2D: Graphics2D) {
        var x = 0
        var y = 0
        var worldX: Int
        var worldY: Int
        var screenX: Int
        var screenY: Int

        mapTileNumber.forEach { tiles ->
            tiles.forEach { tile ->
                worldX = x * TILE_SIZE
                worldY = y * TILE_SIZE

                screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX
                screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY

                if (
                    (worldX + TILE_SIZE) > (gamePanel.player.worldX - gamePanel.player.screenX) &&
                    (worldX - TILE_SIZE) < gamePanel.player.worldX + gamePanel.player.screenX &&
                    (worldY + TILE_SIZE) > gamePanel.player.worldY - gamePanel.player.screenY &&
                    (worldY - TILE_SIZE) < gamePanel.player.worldY + gamePanel.player.screenY
                ) {
                    graphics2D.drawImage(tileDesigns[tile]?.image, screenX, screenY, TILE_SIZE, TILE_SIZE, null)
                }
                x++
            }
            x = 0
            y++
        }

    }
}