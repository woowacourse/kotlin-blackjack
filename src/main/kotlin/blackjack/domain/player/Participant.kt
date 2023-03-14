package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.result.Result

class Participant(name: String, val betAmount: Int = 0, cards: Cards = Cards()) : Player(name, cards) {

    override fun checkProvideCardPossible(): Boolean = (cards.calculateScore() < PARTICIPANT_MORE_CARD_CRITERIA)

    fun calculateResult(dealer: Dealer): Result {
        return when {
            (isBust) -> Result.LOSE
            (dealer.isBust) -> Result.WIN
            (isBlackjack and !dealer.isBlackjack) -> Result.WIN
            (!isBlackjack and dealer.isBlackjack) -> Result.LOSE
            (isBlackjack and dealer.isBlackjack) -> Result.DRAW
            (cards.calculateScore() > dealer.cards.calculateScore()) -> Result.WIN
            (cards.calculateScore() < dealer.cards.calculateScore()) -> Result.LOSE
            else -> Result.DRAW
        }
    }

    companion object {
        const val PARTICIPANT_MORE_CARD_CRITERIA = 21
    }
}
