package blackjack.domain.participant

import blackjack.domain.TrumpCard

sealed interface Participant {
    val cards: List<TrumpCard>

    fun getInitializedCards(
        initializedCardCount: Int,
        card: TrumpCard,
    )

    fun addCard(card: TrumpCard)

    fun hasAce(): Boolean

    fun isBust(): Boolean
}
