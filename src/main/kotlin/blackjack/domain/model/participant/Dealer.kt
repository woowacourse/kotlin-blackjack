package blackjack.domain.model.participant

import blackjack.domain.model.card.Card

class Dealer(
    name: String = DEFAULT_NAME,
) : GameParticipant(name = name) {
    fun getFirstCard(): Card = handCards.getCardByIndex(0)

    override fun isDrawFinish(): Boolean {
        val bestCardValue = handCards.calculateBestCardValue()
        return bestCardValue <= DEALER_DRAW_LIMIT
    }

    companion object {
        private const val DEFAULT_NAME = "딜러"
        private const val DEALER_DRAW_LIMIT = 16
    }
}
