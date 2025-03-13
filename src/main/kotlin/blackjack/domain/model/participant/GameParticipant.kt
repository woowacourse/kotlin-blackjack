package blackjack.domain.model.participant

import blackjack.domain.model.card.HandCards

abstract class GameParticipant(
    val name: String,
) {
    val handCards = HandCards()

    fun cardSize() = handCards.cards.size

    fun isInitHandCard() = handCards.cards.size == HandCards.INIT_CARD_SIZE

    abstract fun isDrawFinish(): Boolean
}
