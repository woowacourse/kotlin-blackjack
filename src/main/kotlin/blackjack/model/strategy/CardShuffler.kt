package blackjack.model.strategy

import blackjack.model.domain.Card

interface CardShuffler {
    fun spread(cards: List<Card>): List<Card>
}
