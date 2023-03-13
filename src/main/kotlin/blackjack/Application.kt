package blackjack

import blackjack.controller.Controller
import blackjack.domain.Card
import blackjack.domain.CardDeck

fun main() {
    Controller(CardDeck(Card.all())).runGame()
}
