import controller.GameController
import view.View

fun main() {
    val gameController = GameController(View())
    gameController.playGame()
}