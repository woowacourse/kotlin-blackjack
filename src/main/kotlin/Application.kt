import controller.GameController
import view.InputView

fun main() {
    val gameController = GameController(InputView())
    gameController.playGame()
}