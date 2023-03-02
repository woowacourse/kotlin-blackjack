package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.Cards.Companion.MAX_SUM_NUMBER

class Participant(name: String) : Player(name) {

    lateinit var result: Result
        private set

    fun isGenerateCardPossible(): Boolean {
        if (cards.sumCardsNumber() > MAX_SUM_NUMBER) return false
        return true
    }
}
