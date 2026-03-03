package arw.simple.game.player

import arw.simple.game.commons.FormativeConstants
import arw.simple.game.handler.KeyHandler
import arw.simple.game.panel.GamePanel
import java.awt.Graphics2D
import javax.imageio.ImageIO

class Player(
    val gamePanel: GamePanel,
    val keyHandler: KeyHandler,
): Entity() {
    init {
        setDefaultValues()
        getPlayerImage()
    }

    fun setDefaultValues() {
        x = 100
        y = 100
        speed = 4
        direction = PlayerMovement.DOWN
    }

    override fun getPlayerImage() {
        runCatching {
            up1 = ImageIO.read(javaClass.getResourceAsStream("/player/boy_up_1.png"))
            up2 = ImageIO.read(javaClass.getResourceAsStream("/player/boy_up_2.png"))
            down1 = ImageIO.read(javaClass.getResourceAsStream("/player/boy_down_1.png"))
            down2 = ImageIO.read(javaClass.getResourceAsStream("/player/boy_down_2.png"))
            left1 = ImageIO.read(javaClass.getResourceAsStream("/player/boy_left_1.png"))
            left2 = ImageIO.read(javaClass.getResourceAsStream("/player/boy_left_2.png"))
            right1 = ImageIO.read(javaClass.getResourceAsStream("/player/boy_right_1.png"))
            right2 = ImageIO.read(javaClass.getResourceAsStream("/player/boy_right_2.png"))
        }.onFailure {
            it.printStackTrace()
        }
    }

    fun update() {
        when (keyHandler.playerMovement) {
            PlayerMovement.UP -> {
                direction = PlayerMovement.UP
                y -= speed
            }
            PlayerMovement.DOWN -> {
                direction = PlayerMovement.DOWN
                y += speed
            }
            PlayerMovement.LEFT -> {
                direction = PlayerMovement.LEFT
                x -= speed
            }
            PlayerMovement.RIGHT -> {
                direction = PlayerMovement.RIGHT
                x += speed
            }
            else -> return
        }

        spriteCounter++

        if (spriteCounter > 15) {
            spriteNumber = if (spriteNumber == 1) 2 else 1
            spriteCounter = 0
        }
    }

    fun draw(graphics2D: Graphics2D) {
        val image = when (direction) {
            PlayerMovement.UP -> if (spriteNumber == 1) up1 else up2
            PlayerMovement.DOWN ->  if (spriteNumber == 1) down1 else down2
            PlayerMovement.LEFT ->  if (spriteNumber == 1) left1 else left2
            PlayerMovement.RIGHT ->  if (spriteNumber == 1) right1 else right2
            else -> return
        }

        graphics2D.drawImage(image, x, y, FormativeConstants.TILE_SIZE, FormativeConstants.TILE_SIZE, null)
    }


}
