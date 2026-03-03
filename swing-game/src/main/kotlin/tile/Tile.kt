package arw.simple.game.model

import java.awt.image.BufferedImage

data class Tile(

    var image: BufferedImage? = null,
    var collision: Boolean = false
)