package blackjack.model.deck

import blackjack.model.deck.Card

fun interface CardMachine {
    fun shuffle(cards: List<Card>): List<Card>
}
