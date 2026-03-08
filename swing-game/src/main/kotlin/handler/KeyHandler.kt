package arw.simple.game.handler

import arw.simple.game.player.PlayerDirection
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class KeyHandler: KeyListener {
    var playerDirection = PlayerDirection.NONE
        private set

    override fun keyTyped(e: KeyEvent?) {}

    override fun keyPressed(e: KeyEvent?) {
        val code = e?.keyCode

        when (code) {
            KeyEvent.VK_W -> playerDirection = PlayerDirection.UP
            KeyEvent.VK_S -> playerDirection = PlayerDirection.DOWN
            KeyEvent.VK_D -> playerDirection = PlayerDirection.RIGHT
            KeyEvent.VK_A -> playerDirection = PlayerDirection.LEFT
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        val code = e?.keyCode

        when (code) {
            KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A -> playerDirection = PlayerDirection.NONE
        }
    }
}