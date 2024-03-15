import blackjack.controller.BlackJackController
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    BlackJackController(InputView(), OutputView()).gameStart()
}
