package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.Cards.Companion.MAX_SUM_NUMBER

class Participant(name: String) : Player(name) {

    lateinit var result: Result
        private set

    override val cardProvideCriteria = MAX_SUM_NUMBER

    fun updateResult(dealerSum: Int) {
        result = calculateResult(dealerSum)
    }
}
