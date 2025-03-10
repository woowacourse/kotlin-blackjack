package blackjack.model.strategy

import blackjack.model.domain.card.Card

class TrueShuffle : CardShuffler {
    override fun spread(cards: List<Card>): List<Card> {
        return cards.shuffled()
    }
}
