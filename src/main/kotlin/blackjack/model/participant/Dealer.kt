package blackjack.model.participant

import blackjack.model.deck.Deck
import blackjack.model.deck.HandCards

class Dealer(deck: Deck) : GameParticipant(HandCards(), deck) {
    init {
        initializeCards()
    }

    fun getFirstCard(): String = handCards.getFirstCard()

    fun isAddCard(): Boolean = calculateScore() < DEALER_HIT_THRESHOLD

    companion object {
        private const val DEALER_HIT_THRESHOLD = 17
    }
}
