import blackjack.controller.Casino
import blackjack.domain.model.card.Deck
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.Views
import java.util.Locale

fun main() {
    val views = Views(InputView(), OutputView(Locale.KOREAN))
    val deck = Deck()
    val casino = Casino(views, deck)
    casino.blackJackGame()
}
