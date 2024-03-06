import blackjack.controller.BlackJack
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    BlackJack(InputView(), OutputView()).gameStart()
}
