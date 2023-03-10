package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.Cards

class Dealer(name: String = "딜러", cards: Cards = Cards()) : Player(name, cards) {

    override fun checkProvideCardPossible(): Boolean {
        if (cards.sumCardsNumber() <= PARTICIPANT_MORE_CARD_CRITERIA) return true
        return false
    }

    fun calculateResults(participants: Participants): DealerResult {
        val results: MutableList<Result> = mutableListOf()
        participants.values.forEach { participant ->
            results.add(calculateResult(participant))
        }
        return DealerResult(results)
    }

    private fun calculateResult(participant: Participant): Result {
        return when {
            (participant.isBurst) -> Result.WIN
            (isBurst) -> Result.LOSE
            (isBlackjack and !participant.isBlackjack) -> Result.WIN
            (!isBlackjack and participant.isBlackjack) -> Result.LOSE
            (isBlackjack and participant.isBlackjack) -> Result.DRAW
            (participant.cards.sumCardsNumber() > cards.sumCardsNumber()) -> Result.LOSE
            (participant.cards.sumCardsNumber() < cards.sumCardsNumber()) -> Result.WIN
            else -> Result.DRAW
        }
    }

    companion object {
        const val PARTICIPANT_MORE_CARD_CRITERIA = 16
    }
}
