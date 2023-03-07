package blackjack

import blackjack.controller.Controller
import blackjack.domain.CardDeck

fun main() {
    Controller(CardDeck()).runGame()
}
