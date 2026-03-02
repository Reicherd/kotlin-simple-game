package arw.simple.game.model

import java.awt.image.BufferedImage

abstract class Entity(
    var x: Int = 0,
    var y: Int = 0,
    var speed: Int = 4,

    var up1: BufferedImage? = null,
    var up2: BufferedImage? = null,
    var down1: BufferedImage? = null,
    var down2: BufferedImage? = null,
    var left1: BufferedImage? = null,
    var left2: BufferedImage? = null,
    var right1: BufferedImage? = null,
    var right2: BufferedImage? = null,

    var direction: PlayerMovement = PlayerMovement.DOWN,

    var spriteCounter: Int = 0,
    var spriteNumber: Int = 1
) {
    abstract fun getPlayerImage()
}
