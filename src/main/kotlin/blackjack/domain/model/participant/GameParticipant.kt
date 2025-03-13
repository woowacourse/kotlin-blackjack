package blackjack.domain.model.participant

import blackjack.domain.model.card.HandCards

abstract class GameParticipant(
    val name: String,
) {
    val handCards = HandCards()

    fun cardSize() = handCards.cards.size

    abstract fun isDrawFinish(): Boolean
}
