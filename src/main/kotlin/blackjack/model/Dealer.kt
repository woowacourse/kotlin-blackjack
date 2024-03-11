package blackjack.model

class Dealer(name: String = DEFAULT_DEALER_NAME) : Participant(name) {
    fun openFirstCard(): Card? {
        return getCards().firstOrNull().also { card ->
            card ?: println(ERROR_CARD_INDEX)
        }
    }

    fun checkShouldDealerDrawCard(): Boolean {
        return getBlackJackScore() <= MIN_HAND_CARD_SCORE
    }

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
        const val ERROR_CARD_INDEX = "딜러가 가지고 있는 카드가 없습니다."
        const val MIN_HAND_CARD_SCORE: Int = 16
    }
}
