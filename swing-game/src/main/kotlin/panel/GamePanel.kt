package arw.simple.game.panel

import arw.simple.game.commons.FormativeConstants
import arw.simple.game.handler.KeyHandler
import arw.simple.game.model.Player
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.time.LocalDateTime
import javax.swing.JPanel

class GamePanel: JPanel {
    companion object {
        private const val MAX_SCREEN_COL = 16
        private const val MAX_SCREEN_ROW = 12
        private const val SCREEN_WIDTH = FormativeConstants.TILE_SIZE * MAX_SCREEN_COL
        private const val SCREEN_HEIGHT = FormativeConstants.TILE_SIZE * MAX_SCREEN_ROW

        private const val FPS = 60
    }

    private val keyHandler: KeyHandler = KeyHandler()
    private val player: Player = Player(this, keyHandler)


    constructor() {
        this.preferredSize = Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)
        this.background = Color.BLACK
        this.isDoubleBuffered = true
        this.addKeyListener(keyHandler)
        this.isFocusable = true

    }

    suspend fun startGameCoroutine() {
        val drawInterval = 1000000000.0 / FPS
        var delta = 0.0
        var lastTime = System.nanoTime()
        var currentTime: Long
        var timer = 0L
        var drawCount = 0

        while (true) {
            currentTime = System.nanoTime()

            delta += (currentTime - lastTime) / drawInterval
            timer += (currentTime - lastTime)
            lastTime = currentTime

            if (delta >= 1) {
                update()

                repaint()

                delta--
                drawCount++
            }

            if (timer >= 1000000000) {
                println("FPS: $drawCount")
                println("Time: ${LocalDateTime.now().second}")
                drawCount = 0
                timer = 0
            }
        }
    }

    private fun update() {
        player.update()
    }

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        val graphics2D = g as Graphics2D

        player.draw(graphics2D)

        graphics2D.dispose()
    }

}