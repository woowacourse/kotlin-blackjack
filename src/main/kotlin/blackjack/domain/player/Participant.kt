package blackjack.domain.player

import blackjack.domain.Result

class Participant(name: String) : Player(name) {

    lateinit var result: Result
        private set

    fun isGenerateCardPossible(): Boolean {
        if (cards.sumCardsNumber() > MAX_SUM_NUMBER) return false
        return true
    }
}
