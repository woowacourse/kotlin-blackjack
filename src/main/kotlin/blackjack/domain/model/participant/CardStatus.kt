package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.HandCards.Companion.INIT_CARD_SIZE

enum class CardStatus {
    BLACKJACK,
    BUST,
    NORMAL,
    ;

    companion object {
        const val BLACKJACK_NUMBER = 21

        fun calculateCardsStatus(cards: Collection<Card>): CardStatus =
            when {
                cards.size == INIT_CARD_SIZE && cards.sumOf { it.getMaximumValue() } == BLACKJACK_NUMBER -> BLACKJACK
                cards.sumOf { it.getMinimumValue() } > BLACKJACK_NUMBER -> BUST
                else -> NORMAL
            }
    }
}
