import blackjack.controller.Casino
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val casino = Casino(inputView, outputView)
    casino.gameStart()
}
