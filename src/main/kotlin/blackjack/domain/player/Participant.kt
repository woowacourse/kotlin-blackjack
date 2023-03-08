package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.result.Result
import blackjack.domain.result.Score

class Participant(
    name: String,
    cards: Cards = Cards()
) : Player(name, cards) {

    lateinit var result: Result
        private set

    fun updateResult(dealerCards: Cards) {
        result = Score.getParticipantResult(dealerCards, cards)
    }

    override fun canHit(): Boolean = cards.sum() < Score.MAX_SUM_NUMBER
}
