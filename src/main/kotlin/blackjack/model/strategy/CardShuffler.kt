package blackjack.model.strategy

import blackjack.model.domain.card.Card

interface CardShuffler {
    fun spread(cards: List<Card>): List<Card>
}
