package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Deck
import blackjack.domain.model.card.HandCards

abstract class GameParticipant(
    val name: String,
) {
    val handCards = HandCards()

    fun showCards(): List<Card> = handCards.currentCards()

    fun drawCard() {
        handCards.addCard(Deck.giveCard())
    }

    abstract fun play()

    abstract fun isDrawFinish(): Boolean
}
