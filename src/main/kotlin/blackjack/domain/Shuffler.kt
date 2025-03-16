package blackjack.domain

import blackjack.domain.card.Card

fun interface Shuffler {
    fun shuffle(cards: List<Card>): List<Card>
}

object RandomShuffler : Shuffler {
    override fun shuffle(cards: List<Card>): List<Card> = cards.shuffled()
}
