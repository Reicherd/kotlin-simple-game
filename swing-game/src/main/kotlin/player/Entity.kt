package arw.simple.game.player

import java.awt.Rectangle
import java.awt.image.BufferedImage

abstract class Entity(
    var worldX: Int = 0,
    var worldY: Int = 0,
    var speed: Int = 4,

    var up1: BufferedImage? = null,
    var up2: BufferedImage? = null,
    var down1: BufferedImage? = null,
    var down2: BufferedImage? = null,
    var left1: BufferedImage? = null,
    var left2: BufferedImage? = null,
    var right1: BufferedImage? = null,
    var right2: BufferedImage? = null,

    var direction: PlayerDirection = PlayerDirection.DOWN,

    var spriteCounter: Int = 0,
    var spriteNumber: Int = 1,

    var solidArea: Rectangle,
    var collisionEnabled: Boolean = false
) {
    abstract fun getPlayerImage()
}
