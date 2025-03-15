package blackjack.domain

import blackjack.domain.card.CardFactory
import blackjack.domain.card.TrumpCard
import java.util.ArrayDeque
import java.util.Deque

class Deck(
    cardFactory: CardFactory,
) {
    private val cards: Deque<TrumpCard> = ArrayDeque()

    init {
        cards.addAll(cardFactory.makeCard())
    }

    fun draw(): TrumpCard {
        if (cards.isEmpty()) {
            throw IllegalArgumentException(ALL_CARD_USED)
        }

        return cards.pop()
    }

    companion object {
        private const val ALL_CARD_USED = "카드가 모두 소진되었습니다."
    }
}
