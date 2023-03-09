package blackjack

import blackjack.controller.BlackJackController
import blackjack.domain.card.CardDeck
import blackjack.view.Console

fun main() {
    val console = Console()
    BlackJackController(console, console).start(CardDeck())
}
