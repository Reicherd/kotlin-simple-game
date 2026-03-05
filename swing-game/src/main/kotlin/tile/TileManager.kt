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
        loadMap("/maps/map01")
    }

    fun getTileImage() {
        runCatching {
            tileDesigns[TileDesign.GRASS] =
                Tile(image = ImageIO.read(javaClass.getResourceAsStream("/tiles/grass.png")))
            tileDesigns[TileDesign.WALL] =
                Tile(image = ImageIO.read(javaClass.getResourceAsStream("/tiles/wall.png")))
            tileDesigns[TileDesign.WATER] =
                Tile(image = ImageIO.read(javaClass.getResourceAsStream("/tiles/water.png")))
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

        mapTileNumber.forEach { tiles ->
            tiles.forEach { tile ->
                graphics2D.drawImage(tileDesigns[tile]?.image, x, y, TILE_SIZE, TILE_SIZE, null)

                x += TILE_SIZE
            }
            x = 0
            y += TILE_SIZE
        }

    }
}