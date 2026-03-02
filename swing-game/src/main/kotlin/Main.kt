package arw.simple.game

import arw.simple.game.panel.GamePanel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.swing.JFrame

suspend fun main() {
    val window = JFrame()

    window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    window.isResizable = false
    window.title = "Simple 2D Game"

    val gamePanel = GamePanel()
    window.add(gamePanel)

    window.setLocationRelativeTo(null)
    window.pack()
    window.isVisible = true

    withContext(Dispatchers.Default) {
        this.launch {
            gamePanel.startGameCoroutine()
        }
    }
}