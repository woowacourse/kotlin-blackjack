package blackjack.domain.player

import blackjack.domain.Result

class Participant(name: String) : Player(name) {

    lateinit var result: Result
        private set

    fun updateResult(dealerSum: Int) {
        result = calculateResult(dealerSum)
    }

    override fun checkProvideCardPossible(): Boolean {
        if (cards.sumCardsNumber() <= PARTICIPANT_MORE_CARD_CRITERIA) return true
        return false
    }

    companion object {
        const val PARTICIPANT_MORE_CARD_CRITERIA = 21
    }
}
