package blackjack.domain.participant

import blackjack.domain.TrumpCard

interface Participant {
    val cards: List<TrumpCard>

    val sumOfCards: Int

    fun addCard(card: TrumpCard)

    fun hasAce(): Boolean

    fun isBust(): Boolean

    fun sum(): Int
}
