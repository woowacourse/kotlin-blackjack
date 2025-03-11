package blackjack.domain.model.participant

import blackjack.domain.model.card.Deck
import blackjack.domain.model.card.HandCards

abstract class GameParticipant(
    val name: String,
) {
    val handCards = HandCards()

    fun cardSize() = handCards.currentCards().size

    fun drawCard() {
        handCards.addCard(Deck.giveCard())
    }

    abstract fun isDrawFinish(): Boolean
}
