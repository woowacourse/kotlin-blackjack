import blackjack.controller.BlackJackController
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
//    BlackJack(InputView(), OutputView()).gameStart()
    BlackJackController(InputView(), OutputView()).gameStart()
}
