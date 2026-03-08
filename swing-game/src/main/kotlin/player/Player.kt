package arw.simple.game.player

import arw.simple.game.commons.FormativeConstants.SCREEN_HEIGHT
import arw.simple.game.commons.FormativeConstants.SCREEN_WIDTH
import arw.simple.game.commons.FormativeConstants.TILE_SIZE
import arw.simple.game.handler.KeyHandler
import arw.simple.game.panel.GamePanel
import java.awt.Graphics2D
import java.awt.Rectangle
import javax.imageio.ImageIO

class Player(
    val gamePanel: GamePanel,
    val keyHandler: KeyHandler,
): Entity(
    solidArea = Rectangle(8, 16, 32, 32)
) {
    val screenX: Int = (SCREEN_WIDTH / 2) - (TILE_SIZE / 2)
    val screenY: Int = (SCREEN_HEIGHT / 2) - (TILE_SIZE / 2)

    init {
        setDefaultValues()
        getPlayerImage()
    }

    fun setDefaultValues() {
        worldX = TILE_SIZE * 23
        worldY = TILE_SIZE * 21
        speed = 4
        direction = PlayerDirection.DOWN

        solidArea = Rectangle(8, 16, 32, 32)
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
        direction = keyHandler.playerDirection

        collisionEnabled = false
        gamePanel.collisionChecker.checkTile(this)

        if (!collisionEnabled) {
            when (direction) {
                PlayerDirection.UP -> worldY -= speed
                PlayerDirection.DOWN -> worldY += speed
                PlayerDirection.LEFT -> worldX -= speed
                PlayerDirection.RIGHT -> worldX += speed
                else -> {}
            }
        }

        spriteCounter++

        if (spriteCounter > 15) {
            spriteNumber = if (spriteNumber == 1) 2 else 1
            spriteCounter = 0
        }
    }

    fun draw(graphics2D: Graphics2D) {
        val image = when (direction) {
            PlayerDirection.UP -> if (spriteNumber == 1) up1 else up2
            PlayerDirection.DOWN ->  if (spriteNumber == 1) down1 else down2
            PlayerDirection.LEFT ->  if (spriteNumber == 1) left1 else left2
            PlayerDirection.RIGHT ->  if (spriteNumber == 1) right1 else right2
            PlayerDirection.NONE ->  down1
        }

        graphics2D.drawImage(image, screenX, screenY, TILE_SIZE, TILE_SIZE, null)
    }


}
