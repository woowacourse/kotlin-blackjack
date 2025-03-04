package blackjack.model.strategy

import blackjack.model.domain.Card

class TrueShuffle : CardShuffler {
    override fun spread(cards: List<Card>): List<Card> {
        return cards.shuffled()
    }
}
