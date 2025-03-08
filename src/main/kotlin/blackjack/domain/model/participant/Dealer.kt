package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card

class Dealer(
    name: String = DEFAULT_NAME,
) : Participant(name = name) {
    fun showFirstCard(): Card = handCards.show().first()

    override fun compareTo(opponent: Participant): GameResult {
        if (opponent.handCards.isBurst()) {
            return GameResult.WIN
        }
        return handCards.compareTo(opponent.handCards)
    }

    override fun isDrawable(): Boolean {
        return handCards.isLessOrSameThan(DEALER_DRAW_CONDITION)
    }

    companion object {
        private const val DEFAULT_NAME = "딜러"
        private const val DEALER_DRAW_CONDITION = 16
    }
}
