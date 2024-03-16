import blackjack.controller.BlackJack
import blackjack.model.card.CardDeck
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    BlackJack(InputView(), OutputView(), CardDeck()).gameStart()
}
