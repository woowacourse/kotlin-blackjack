package blackjack.model.participant

import blackjack.model.ResultCalculator.BUST_NUMBER
import blackjack.model.ResultCalculator.calculateTotalScore
import blackjack.model.card.Card

class Dealer(
    name: String = DEALER_NAME,
) : Participant(name) {
    fun isMoreCard() = calculateTotalScore(cards) < DEALER_MORE_CARD_MINIMUM

    override fun getInitialCard(): List<Card> = listOf(cards.first())

    override fun isBust(): Boolean = calculateTotalScore(cards) > BUST_NUMBER

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_MORE_CARD_MINIMUM = 17
    }
}
