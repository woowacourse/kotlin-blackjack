import blackjack.controller.Casino
import blackjack.domain.model.card.Deck
import blackjack.view.InputView
import blackjack.view.OutputView
import java.util.Locale

fun main() {
    val inputView = InputView()
    val outputView = OutputView(Locale.KOREAN)
    val deck = Deck()
    val casino = Casino(inputView, outputView, deck)
    casino.blackJackGame()
}
