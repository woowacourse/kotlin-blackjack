package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.Cards

class Participant(name: String, cards: Cards = Cards()) : Player(name, cards) {

    override fun checkProvideCardPossible(): Boolean = (cards.calculateScore() < PARTICIPANT_MORE_CARD_CRITERIA)

    fun calculateResult(dealer: Dealer): ParticipantResult {
        val result = when {
            (isBurst) -> Result.LOSE
            (dealer.isBurst) -> Result.WIN
            (isBlackjack and !dealer.isBlackjack) -> Result.WIN
            (!isBlackjack and dealer.isBlackjack) -> Result.LOSE
            (isBlackjack and dealer.isBlackjack) -> Result.DRAW
            (cards.calculateScore() > dealer.cards.calculateScore()) -> Result.WIN
            (cards.calculateScore() < dealer.cards.calculateScore()) -> Result.LOSE
            else -> Result.DRAW
        }
        return ParticipantResult(name, result)
    }

    companion object {
        const val PARTICIPANT_MORE_CARD_CRITERIA = 21
    }
}
