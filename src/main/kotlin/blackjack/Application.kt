package blackjack

import blackjack.controller.Controller
import blackjack.domain.carddeck.CardDeck
import blackjack.domain.carddeck.cardnumbergenerator.RandomCardNumberGenerator
import blackjack.domain.carddeck.shapegenerator.RandomShapeGenerator

fun main() {
    Controller(CardDeck(RandomShapeGenerator(), RandomCardNumberGenerator())).runGame()
}
