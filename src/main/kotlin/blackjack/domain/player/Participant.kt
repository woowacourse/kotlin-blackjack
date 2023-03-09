package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.Cards

class Participant(name: String, cards: Cards = Cards()) : Player(name, cards) {

    override fun checkProvideCardPossible(): Boolean {
        if (cards.sumCardsNumber() <= PARTICIPANT_MORE_CARD_CRITERIA) return true
        return false
    }

    fun calculateResult(other: Player): PlayerResult {
        val result = when {
            (isBurst) -> Result.LOSE
            (other.isBurst) -> Result.WIN
            (isBlackjack and !other.isBlackjack) -> Result.WIN
            (!isBlackjack and other.isBlackjack) -> Result.LOSE
            (isBlackjack and other.isBlackjack) -> Result.DRAW
            (cards.sumCardsNumber() > other.cards.sumCardsNumber()) -> Result.WIN
            (cards.sumCardsNumber() < other.cards.sumCardsNumber()) -> Result.LOSE
            else -> Result.DRAW
        }
        return PlayerResult(mapOf(name to result))
    }

    companion object {
        const val PARTICIPANT_MORE_CARD_CRITERIA = 21
    }
}
