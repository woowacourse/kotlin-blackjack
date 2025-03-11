package blackjack.domain.model.participant

import blackjack.domain.model.card.Card

enum class CardStatus {
    BLACKJACK,
    BUST,
    NORMAL,
    ;

    companion object {
        const val BLACKJACK_NUMBER = 21

        fun calculateCardsStatus(cards: Collection<Card>): CardStatus =
            when {
                cards.size == 2 && cards.sumOf { it.getMaximumValue() } == BLACKJACK_NUMBER -> BLACKJACK
                cards.sumOf { it.getMinimumValue() } > BLACKJACK_NUMBER -> BUST
                else -> NORMAL
            }
    }
}
