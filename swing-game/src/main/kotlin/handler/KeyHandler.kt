package arw.simple.game.handler

import arw.simple.game.model.PlayerMovement
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class KeyHandler: KeyListener {
    var playerMovement = PlayerMovement.NONE
        private set

    override fun keyTyped(e: KeyEvent?) {}

    override fun keyPressed(e: KeyEvent?) {
        val code = e?.keyCode

        when (code) {
            KeyEvent.VK_W -> playerMovement = PlayerMovement.UP
            KeyEvent.VK_S -> playerMovement = PlayerMovement.DOWN
            KeyEvent.VK_D -> playerMovement = PlayerMovement.RIGHT
            KeyEvent.VK_A -> playerMovement = PlayerMovement.LEFT
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        val code = e?.keyCode

        when (code) {
            KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A -> playerMovement = PlayerMovement.NONE
        }
    }
}