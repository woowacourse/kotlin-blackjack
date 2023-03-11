package blackjack.domain.player

import blackjack.domain.EarningRate
import blackjack.domain.card.Cards

class Participant(name: String, val betAmount: Int = 0, cards: Cards = Cards()) : Player(name, cards) {

    override fun checkProvideCardPossible(): Boolean = (cards.calculateScore() < PARTICIPANT_MORE_CARD_CRITERIA)

    fun calculateEarningRate(dealer: Dealer): ParticipantEarningRate {
        val earningRate = when {
            (isBurst) -> EarningRate.LOSE
            (dealer.isBurst) -> EarningRate.WIN
            (isBlackjack and !dealer.isBlackjack) -> EarningRate.BLACKJACK_WIN
            (!isBlackjack and dealer.isBlackjack) -> EarningRate.LOSE
            (isBlackjack and dealer.isBlackjack) -> EarningRate.DRAW
            (cards.calculateScore() > dealer.cards.calculateScore()) -> EarningRate.WIN
            (cards.calculateScore() < dealer.cards.calculateScore()) -> EarningRate.LOSE
            else -> EarningRate.DRAW
        }
        return ParticipantEarningRate(name, earningRate)
    }

    companion object {
        const val PARTICIPANT_MORE_CARD_CRITERIA = 21
    }
}
