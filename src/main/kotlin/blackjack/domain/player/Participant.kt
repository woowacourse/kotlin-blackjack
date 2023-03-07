package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.Cards.Companion.MAX_SUM_NUMBER

class Participant(name: String) : Player(name) {

    lateinit var result: Result
        private set

    fun updateResult(dealerSum: Int) {
        result = Result.valueOf(dealerSum, cards.sum())
    }

    override fun canHit(): Boolean = cards.sum() < MAX_SUM_NUMBER
}
